package com.rabbit.designpatterns.strategy.guava;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.rabbit.designpatterns.strategy.InMessage;
import com.rabbit.designpatterns.strategy.MsgType;
import com.rabbit.designpatterns.strategy.OutMessage;

@SuppressWarnings("hiding")
public class Rule {

    private final MessageRouter routerBuilder;

    private boolean async = true;

    private MsgType msgType;

    private boolean reEnter = false;

    private List<Function<InMessage, OutMessage>> handlers = Lists.newArrayList();

    private Predicate<InMessage> interceptor;

    protected Rule(MessageRouter routerBuilder) {
      this.routerBuilder = routerBuilder;
    }

    /**
     * 设置是否异步执行，默认是true
     * @param async
     * @return
     */
    public Rule async(boolean async) {
      this.async = async;
      return this; 
    }

    /**
     * 如果msgType等于某值
     * @param msgType
     * @return
     */
    public Rule msgType(MsgType msgType) {
      this.msgType = msgType;
      return this;
    }

    /**
     * 设置微信消息拦截器
     * @param interceptor
     * @param otherInterceptors
     * @return
     */
    public Rule and(Predicate<InMessage> interceptor) {
    	this.interceptor = Predicates.and(this.interceptor, interceptor);
    	return this;
    }
    
    public Rule or(Predicate<InMessage> interceptor) {
        this.interceptor = Predicates.or(this.interceptor, interceptor);
        return this;
    }
    
    public Rule not(Predicate<InMessage> interceptor) {
        this.interceptor = Predicates.not(interceptor);
        return this;
    }

    /**
     * 设置微信消息处理器
     * @param handler
     * @return
     */
    public Rule handler(Function<InMessage, OutMessage> handler) {
      return handler(handler, (Function<InMessage, OutMessage>[]) null);
    }

    /**
     * 设置微信消息处理器
     * @param handler
     * @param otherHandlers
     * @return
     */
    public Rule handler(Function<InMessage, OutMessage> handler, Function<InMessage, OutMessage>... otherHandlers) {
      this.handlers.add(handler);
      if (otherHandlers != null && otherHandlers.length > 0) {
        for (Function<InMessage, OutMessage> i : otherHandlers) {
          this.handlers.add(i);
        }
      }
      return this;
    }

    /**
     * 规则结束，代表如果一个消息匹配该规则，那么它将不再会进入其他规则
     * @return
     */
    public MessageRouter end() {
      this.routerBuilder.getRules().add(this);
      return this.routerBuilder;
    }

    /**
     * 规则结束，但是消息还会进入其他规则
     * @return
     */
    public MessageRouter next() {
      this.reEnter = true;
      return end();
    }

    protected boolean test(InMessage inMessage) {
      return (this.msgType == null || this.msgType.equals(inMessage.getMsgType()));
         /* (this.agentId == null || this.agentId.equals(wxMessage.getAgentId()))
          &&
          (this.msgType == null || this.msgType.equals(wxMessage.getMsgType()))
          &&
          (this.event == null || this.event.equals(wxMessage.getEvent()))
          &&
          (this.eventKey == null || this.eventKey.equals(wxMessage.getEventKey()))
          &&
          (this.content == null || this.content.equals(wxMessage.getContent() == null ? null : wxMessage.getContent().trim()))
          &&
          (this.rContent == null || Pattern.matches(this.rContent, wxMessage.getContent() == null ? "" : wxMessage.getContent().trim()))*/
    }

    /**
     * 处理微信推送过来的消息
     * @param inMessage
     * @return true 代表继续执行别的router，false 代表停止执行别的router
     */
    protected OutMessage service(InMessage inMessage) {
      // 如果拦截器不通过
		if (!interceptor.apply(inMessage)) {
		  return null;
		}

		// 交给handler处理
		OutMessage res = null;
		for (Function<InMessage, OutMessage> handler : this.handlers) {
			// 返回最后handler的结果
			res = handler.apply(inMessage);
		}
		
		return res;
	}

	public boolean isAsync() {
		return async;
	}

	public void setAsync(boolean async) {
		this.async = async;
	}

	public MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}

	public boolean isReEnter() {
		return reEnter;
	}

	public void setReEnter(boolean reEnter) {
		this.reEnter = reEnter;
	}
	
}