/*
 * Copyright 2018 Rundeck, Inc. (http://rundeck.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rundeck.jaas.jetty

import org.eclipse.jetty.jaas.spi.PropertyFileLoginModule
import spock.lang.Specification

import javax.security.auth.Subject


class JettyRolePropertyFileLoginModuleTest extends Specification {
    def "Initialize without hotReload set"() {
        when:
        JettyRolePropertyFileLoginModule module = new JettyRolePropertyFileLoginModule()
        module.initialize(new Subject(), null, [:], [useFirstPass:"true"])

        then:
        module.module instanceof PropertyFileLoginModule
    }

    def "Initialize hotReload set to true"() {
        when:
        JettyRolePropertyFileLoginModule module = new JettyRolePropertyFileLoginModule()
        module.initialize(new Subject(),null,[:],[useFirstPass:"true",hotReload:"true"])

        then:
        module.module instanceof ReloadablePropertyFileLoginModule
    }
}
