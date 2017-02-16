c/**
 *  open door with switch
 *
 *  Copyright 2016 George Ricketts
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "open windowShades with switch",
    namespace: "grickettsnz",
    author: "George Ricketts",
    description: "Open lock with switch",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
	
    section("Open lock when switch is on:") {
        input "theswitch", "capability.switch", required: true
    }
    section("Open these blinds") {
        input "blind1", "capability.windowShade", required: true
        input "blind2", "capability.windowShade", required: false
        input "blind3", "capability.windowShade", required: false
    }
	
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	
	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
	subscribe(theswitch, "switch.on", myOnHandler)
    subscribe(theswitch, "switch.off", myOffHandler)
}

def myOffHandler(evt) {
	blind1.open()
    pause(300)
    blind2.open()
    pause(300)
    blind3.open()
}

def myOnHandler(evt) {
	
    blind1.close()
    pause(300)
    blind2.close()
    pause(300)
    blind3.close()
    
}
// TODO: implement event handlers