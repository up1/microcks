/*
 * Licensed to Laurent Broudoux (the "Author") under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Author licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.github.lbroudoux.microcks.util.test;

import com.github.lbroudoux.microcks.domain.Operation;
import com.github.lbroudoux.microcks.domain.Request;
import com.github.lbroudoux.microcks.domain.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A runner responsible for running test on a given service endpoint. The tests consists in sending a
 * bunch of reference requests to the endpoint and extract informations from acquired responses (or communication
 * failures !) in order to determine if test has passed or not.
 * @author laurent
 */
public abstract class AbstractTestRunner<T>{

   /**
    * Run a test for a specified service and operation.
    * @param service The service under test
    * @param operation The operation to test
    * @param requests A set of reference requests for operation
    * @param endpointUrl The URL of endpoint to test
    * @param method The method that applies for requesting service (retrieved using buildMethod() method) 
    * @return A list of TestReturn corresponding to the result of test for each reference requests. Returns indices
    * matches refernce request indices.
    * @throws java.net.URISyntaxException
    * @throws java.io.IOException
    */
   public abstract List<TestReturn> runTest(Service service, Operation operation, List<Request> requests, String endpointUrl, T method) throws URISyntaxException, IOException;
   
   /**
    * (interpretation is subject to implementation)
    * @param method
    * @return
    */
   public abstract T buildMethod(String method);
   
   /** Build a single string value from values set. */
   protected String buildValue(Set<String> values){
      if (values == null || values.isEmpty()){
         return null;
      }
      StringBuilder result = new StringBuilder();
      Iterator<String> iterator = values.iterator();
      result.append(iterator.next());
      do {
         result.append(",");
         if (iterator.hasNext()){
            result.append(iterator.next());
         }
      } while (iterator.hasNext());
      
      return result.toString();
   }
}
