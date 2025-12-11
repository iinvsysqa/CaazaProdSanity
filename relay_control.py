import sys
import hid
import time

def control_relay(command):
    try:
        device = hid.device()
        device.open(0x0519, 0x2018)

        if command == "on":
            device.write([0x00, 0xF1])
            print("Relay turned ON")
        elif command == "off":
            device.write([0x00, 0x01])
            print("Relay turned OFF")
        else:
            print("Unknown command:", command)

        device.close()

    except Exception as e:
        print("ERROR:", e)

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Usage: python relay_control.py [on|off]")
        sys.exit(1)

    control_relay(sys.argv[1])

    