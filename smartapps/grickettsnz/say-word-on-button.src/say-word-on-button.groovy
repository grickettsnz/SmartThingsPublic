/**
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
    name: "say word on button",
    namespace: "grickettsnz",
    author: "George Ricketts",
    description: "Say word on button",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
	
    section("Button to use for trigger:") {
        input "thebutton", "capability.button", required: true
    }
    section("Speaker") {
        input "thespeaker", "capability.speechSynthesis", required: true
        input "word", "Words to say", required: true
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
	subscribe(thebutton, "button", myHandler)
}

def myHandler(evt) {
	thespeaker.speak(word)
}
// TODO: implement event handlers