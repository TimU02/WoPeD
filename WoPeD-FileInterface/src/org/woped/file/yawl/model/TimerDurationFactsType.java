//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.18 at 08:57:29 AM CEST 
//


package org.woped.file.yawl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TimerDurationFactsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TimerDurationFactsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ticks" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="interval" type="{http://www.yawlfoundation.org/yawlschema}TimerIntervalType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimerDurationFactsType", propOrder = {
    "ticks",
    "interval"
})
public class TimerDurationFactsType {

    protected long ticks;
    @XmlElement(required = true)
    protected TimerIntervalType interval;

    /**
     * Gets the value of the ticks property.
     * 
     */
    public long getTicks() {
        return ticks;
    }

    /**
     * Sets the value of the ticks property.
     * 
     */
    public void setTicks(long value) {
        this.ticks = value;
    }

    /**
     * Gets the value of the interval property.
     * 
     * @return
     *     possible object is
     *     {@link TimerIntervalType }
     *     
     */
    public TimerIntervalType getInterval() {
        return interval;
    }

    /**
     * Sets the value of the interval property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimerIntervalType }
     *     
     */
    public void setInterval(TimerIntervalType value) {
        this.interval = value;
    }

}