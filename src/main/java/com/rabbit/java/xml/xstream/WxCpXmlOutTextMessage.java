package com.rabbit.java.xml.xstream;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WxCpXmlOutTextMessage extends WxCpXmlOutMessage {
  
  @XmlElement(name="Content")
  private String content;

  public WxCpXmlOutTextMessage() {
    super.msgType = "text";
  }
  
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
