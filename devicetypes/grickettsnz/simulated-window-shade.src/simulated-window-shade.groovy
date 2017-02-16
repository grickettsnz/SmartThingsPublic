/**
 *  Z-Wave Garage Door Opener
 *
 *  Copyright 2014 SmartThings
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
metadata {
	definition (name: "Simulated Window Shade", namespace: "grickettsnz", author: "George Ricketts") {
		capability "Actuator"
		capability "Window Shade"
		capability "Refresh"
	}

	simulator {
		
	}

	tiles {
		standardTile("toggle", "device.windowShade", width: 2, height: 2) {
			state("closed", label:'${name}', action:"open", icon:"st.doors.garage.garage-closed", backgroundColor:"#79b821", nextState:"opening")
			state("open", label:'${name}', action:"close", icon:"st.doors.garage.garage-open", backgroundColor:"#ffa81e", nextState:"closing")
			state("opening", label:'${name}', action:"close", icon:"st.doors.garage.garage-closed", backgroundColor:"#ffe71e")
			state("closing", label:'${name}', action:"open", icon:"st.doors.garage.garage-open", backgroundColor:"#ffe71e")
			
		}
		standardTile("open", "device.windowShade", inactiveLabel: false, decoration: "flat") {
			state "default", label:'open', action:"open", icon:"st.doors.garage.garage-opening"
		}
		standardTile("close", "device.windowShade", inactiveLabel: false, decoration: "flat") {
			state "default", label:'close', action:"close", icon:"st.doors.garage.garage-closing"
		}

		main "toggle"
		details(["toggle", "open", "close"])
	}
}

def parse(String description) {
	log.trace "parse($description)"
}



def open() {
	sendEvent(name: "windowShade", value: "opening")
    state.nextState = "open"
    runIn(15, finishEvent)
}

def close() {
    sendEvent(name: "windowShade", value: "closing")
    state.nextState = "closed"
	runIn(15, finishEvent)
}

def finishEvent() {
    sendEvent(name: "windowShade", value: state.nextState)
}