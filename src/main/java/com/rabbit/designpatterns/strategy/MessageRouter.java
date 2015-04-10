package com.rabbit.designpatterns.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 * 微信消息路由器，通过代码化的配置，把来自微信的消息交给handler处理
 *
 * 说明：
 * 1. 配置路由规则时要按照从细到粗的原则，否则可能消息可能会被提前处理
 * 2. 默认情况下消息只会被处理一次，除非使用 {@link Rule#next()}
 * 3. 规则的结束必须用{@link Rule#end()}或者{@link Rule#next()}，否则不会生效
 *
 * 使用方法：
 * MessageRouter router = new MessageRouter();
 * router
 *   .rule()
 *       .msgType("MSG_TYPE").event("EVENT").eventKey("EVENT_KEY").content("CONTENT")
 *       .interceptor(interceptor, ...).handler(handler, ...)
 *   .end()
 *   .rule()
 *       // 另外一个匹配规则
 *   .end()
 * ;
 *
 * // 将WxXmlMessage交给消息路由器
 * router.route(message);
 *
 * </pre>
 * @author Ethan
 *
 */
public class MessageRouter {

	private final List<Rule> rules = new ArrayList<Rule>();

	private final ExecutorService es = Executors.newCachedThreadPool();

	/**
	 * 开始一个新的Route规则
	 * @return
	 */
	public Rule rule() {
	  return new Rule(this);
	}

	/**
	 * 处理微信消息
	 * @param inMessage
	 */
	public OutMessage route(final InMessage inMessage) {
		final List<Rule> matchRules = new ArrayList<Rule>();
		// 收集匹配的规则
		for (final Rule rule : rules) {
		  if (rule.test(inMessage)) {
		    matchRules.add(rule);
		  }
		}
	
		if (matchRules.size() == 0) {
		  return null;
		}
	
		if (matchRules.get(0).isAsync()) {
			// 只要第一个是异步的，那就异步执行
			// 在另一个线程里执行
			es.submit(new Runnable() {
			    @Override
				public void run() {
			    	for (final Rule rule : matchRules) {
			    		rule.service(inMessage);
			    		if (!rule.isReEnter()) {
			    			break;
			    		}
			    	}
				}
			});
		  return null;
		}
	
		OutMessage res = null;
		for (final Rule rule : matchRules) {
		  // 返回最后一个匹配规则的结果
			res = rule.service(inMessage);
		    if (!rule.isReEnter()) {
		    	break;
		    }
		}
		
		return res;
	}

	public List<Rule> getRules() {
		return rules;
	}

}
