/*
 * #%L
 * Camel :: Example :: Management :: TESB container
 * %%
 * Copyright (C) 2011-2020 Talend Inc.
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

package org.talend.esb.camel.example.management;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.camel.language.XPath;


/**
 * @version
 */
public class StockService {

    private final String[] symbols = {"IBM", "APPLE", "ORCL"};
    private Map<String, Integer> stat = new ConcurrentHashMap<String, Integer>();

    public String transform(@XPath("/stock/symbol/text()") String symbol, @XPath("/stock/value/text()") String value) {
        Integer hits = stat.get(symbol);
        if (hits == null) {
            hits = 1;
        } else {
            hits++;
        }
        stat.put(symbol, hits);

        return symbol + '@' + hits;
    }

    public String getHits() {
        return stat.toString();
    }

    public String createRandomStocks() {
        Random ran = new Random();

        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<stocks>\n");
        for (int i = 0; i < 100; i++) {
            int winner = ran.nextInt(symbols.length);
            String symbol = symbols[winner];
            int value = ran.nextInt(1000);
            xml.append("<stock>");
            xml.append("<symbol>").append(symbol).append("</symbol>");
            xml.append("<value>").append(value).append("</value>");
            xml.append("</stock>\n");
        }
        xml.append("</stocks>");

        return xml.toString();
    }

}
