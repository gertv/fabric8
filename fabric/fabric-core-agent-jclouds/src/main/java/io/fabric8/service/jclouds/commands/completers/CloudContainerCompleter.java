/**
 *  Copyright 2005-2015 Red Hat, Inc.
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
package io.fabric8.service.jclouds.commands.completers;

import io.fabric8.api.Container;
import io.fabric8.api.FabricService;
import io.fabric8.boot.commands.support.AbstractContainerCompleter;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.karaf.shell.console.Completer;

@Component(immediate = true)
@Service({ CloudContainerCompleter.class, Completer.class })
public class CloudContainerCompleter extends AbstractContainerCompleter {

    @Reference
    private FabricService fabricService;

    @Activate
    void activate() {
        activateComponent();
    }

    @Deactivate
    void deactivate() {
        deactivateComponent();
    }

    @Override
    public FabricService getFabricService() {
        return fabricService;
    }

    public void setFabricService(FabricService fabricService) {
        this.fabricService = fabricService;
    }

    @Override
    public boolean apply(Container container) {
        if (container != null && container.getMetadata() != null
                && container.getMetadata().getCreateOptions() != null
                && container.getMetadata().getCreateOptions().getProviderType().equals("jclouds")) {
            return true;
        }
        else return false;
    }
}
