/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package org.example.ipaas;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.salesforce.SalesforceComponent;
import org.apache.camel.component.salesforce.SalesforceLoginConfig;
import org.apache.camel.salesforce.dto.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import twitter4j.Status;


/**
 * A spring-boot application that includes a Camel route builder to setup the Camel routes
 */
@SpringBootApplication
@ImportResource({"classpath:spring/camel-context.xml"})
public class Application extends RouteBuilder {
	
	@Value("${salesforce.clientId}")
	private String clientId;
	
	@Value("${salesforce.secret}")
	private String secret;
	
	@Value("${salesforce.username}")
	private String userName;
	
	@Value("${salesforce.password}")
	private String password;

    // must have a main method spring-boot can run
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void configure() throws Exception {
  
        from("twitter://timeline/mentions?type=polling&delay=5000" +
        		"&consumerKey={{twitter.consumer.key}}" + 
        		"&consumerSecret={{twitter.consumer.secret}}" + 
        		"&accessToken={{twitter.access.token}}" + 
        		"&accessTokenSecret={{twitter.access.token.secret}}")
        	.to("bean:mapTweet")
        	.to("salesforce:createSObject");
    }
    
    @Bean
    public DataMapping mapTweet() {
    	return new DataMapping();
    }
    
    @Bean
    public SalesforceComponent salesforce() {
    	SalesforceComponent salesforce = new SalesforceComponent();
    	salesforce.setPackages("org.apache.camel.salesforce.dto");
    	SalesforceLoginConfig config = new SalesforceLoginConfig();
		config.setClientId(clientId);
		config.setClientSecret(secret);
		config.setUserName(userName);
		config.setPassword(password);
		salesforce.setLoginConfig(config);
		return salesforce;
    }
}
