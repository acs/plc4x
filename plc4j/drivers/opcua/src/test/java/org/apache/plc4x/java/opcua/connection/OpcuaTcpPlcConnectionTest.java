/*
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
*/
package org.apache.plc4x.java.opcua.connection;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 */
public class OpcuaTcpPlcConnectionTest {

    private final String[] validTCPOPC = {
        "localhost",
        "www.google.de",
        "127.0.0.1",
        "254.254.254.254"
    };
    private final int[] validPorts = {
        1337,
        42,
        1,
        24152
    };
    private final String[] nDiscoveryParams = {
        "discovery=false"
    };

    @BeforeEach
    public void before() {
    }

    @AfterEach
    public void after() {

    }

    @Test
    public void discoveryParamTest() {
        for (String address :
            validTCPOPC) {
            for (int port :
                validPorts) {
                for (String discoveryParam :
                    nDiscoveryParams) {
                    String param = "";
                    param += discoveryParam;

                    OpcuaConnectionFactory opcuaConnectionFactory = new OpcuaConnectionFactory();

                    try {
                        OpcuaTcpPlcConnection tcpPlcConnection = opcuaConnectionFactory.opcuaTcpPlcConnectionOf(InetAddress.getByName(address), port, param, 5000);
                        assertTrue(tcpPlcConnection.isSkipDiscovery());
                    } catch (UnknownHostException e) {
                        fail("Testadress is no valid InetAddress: " + e);
                    }
                }
            }


        }

    }
}
