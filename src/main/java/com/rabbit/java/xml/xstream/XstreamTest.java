/**
 * 
 */
package com.rabbit.java.xml.xstream;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

/**
 * @author Ethan
 *
 */
public class XstreamTest {
	@Test
	public void testX() {
		XStream x = new XStream();
		/*x.aliasPackage("com.rabbit.java.xml.xstream", "");*/
		x.aliasType("xml", WxCpXmlMessage.class);
		WxCpXmlMessage message = new WxCpXmlMessage();
		message.setAgentId(1);
		message.setContent("hehe");
		message.setDescription("haha");
		
		System.out.println(x.toXML(message));
		
		String xml = "<xml>" +
						  "<agentId>1</agentId>" +
						  "<content>hehe</content>" +
						  "<description>haha</description>" +
						  "</xml>";
		message = (WxCpXmlMessage) x.fromXML(xml);
	}
}
