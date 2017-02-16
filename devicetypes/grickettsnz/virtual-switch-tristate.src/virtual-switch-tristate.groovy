metadata {
        definition (name: "Virtual Switch Tristate", namespace: "grickettsnz", author: "grickettsnz") {
        capability "Switch"
        capability "Refresh"
    }

	// simulator metadata
	simulator {
	}

	// UI tile definitions
	tiles {
		standardTile("on", "device.switch", width: 2, height: 2, canChangeIcon: true) {
			state "default", label: 'Turn On', action: "switch.on", icon: "st.Kids.kid10", backgroundColor: "#79b821", nextState: "on"
		}
		standardTile("off", "device.switch", inactiveLabel: false, decoration: "flat") {
			state "default", label: 'Turn Off', action: "switch.on", icon: "st.Kids.kid10", backgroundColor: "#ffffff", nextState: "off"
		}        
       
       

		main(["on"])
		details(["on", "off"])
	}
}

def parse(String description) {
}

def on() {
	sendEvent(name: "switch", value: "on")
    runIn(3, goUnknown)
    log.info "On"
}

def off() {
	sendEvent(name: "switch", value: "off")
    runIn(3, goUnknown)
    log.info "Off"
}

def refresh() {
    log.info "refresh"
}

def goUnknown()
{
	sendEvent(name: "switch", value: "Unknown")
}