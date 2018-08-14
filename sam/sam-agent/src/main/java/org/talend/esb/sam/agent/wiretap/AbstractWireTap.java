/*
 * #%L
 * Service Activity Monitoring :: Agent
 * %%
 * Copyright (C) 2011 - 2012 Talend Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.talend.esb.sam.agent.wiretap;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;

public abstract class AbstractWireTap extends AbstractPhaseInterceptor<Message> {

    private static final String MESSAGE_CONTENT_PROPERTY_NAME = "org.talend.esb.sam.agent.log.messageContent";

    private final boolean logMessageContent;
    private final boolean logMessageContentOverride;

    protected AbstractWireTap(String phase, boolean logMessageContent, boolean logMessageContentOverride) {
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
