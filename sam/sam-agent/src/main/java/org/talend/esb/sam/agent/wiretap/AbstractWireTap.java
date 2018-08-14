package org.talend.esb.sam.agent.wiretap;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;

public abstract class AbstractWireTap extends AbstractPhaseInterceptor<Message> {

    private static final String MESSAGE_CONTENT_PROPERTY_NAME = "org.talend.esb.sam.agent.log.messageContent";

    private final boolean logMessageContent;
    private final boolean logMessageContentOverride;

    public AbstractWireTap(String phase, boolean logMessageContent, boolean logMessageContentOverride) {
        super(phase);
        this.logMessageContent = logMessageContent;
        this.logMessageContentOverride = logMessageContentOverride;
    }

    protected final boolean isLogMessageContent(Message message) {
        /*
         * If controlling of logging behavior is not allowed externally
         * then log according to global property value
         */
        if (!logMessageContentOverride) {
            return logMessageContent;
        }

        Object logMessageContentProp = message.getContextualProperty(MESSAGE_CONTENT_PROPERTY_NAME);

        if (null == logMessageContentProp) {
            return logMessageContent;
        }
        
        if (logMessageContentProp instanceof Boolean) {
            return ((Boolean) logMessageContentProp).booleanValue();
        }
        
        if (logMessageContentProp instanceof String) {
            String logMessageContentVal = (String) logMessageContentProp;

            if (logMessageContentVal.equalsIgnoreCase("true")) {
                return true;
            }
            
            if (logMessageContentVal.equalsIgnoreCase("false")) {
                return false;
            }
        }

        return logMessageContent;
    }
}
