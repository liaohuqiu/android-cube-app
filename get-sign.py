import os
import sys
import subprocess
from subprocess import call
if len(sys.argv) < 2:
    print >> sys.stderr, "Usage: %s <keystore_file>" % sys.argv[0]
    sys.exit(1)

keystore_file = sys.argv[1]
keystore_file = os.path.expanduser(keystore_file)
keystore_file = os.path.realpath(keystore_file)

cmd = 'keytool -list -alias androiddebugkey -keystore %s -storepass android -v' % (keystore_file)
print cmd
print '---------\n'
cmd = cmd.split()

output = subprocess.Popen(cmd, stdout=subprocess.PIPE).communicate()[0]
print output
print '---------\n'
print output.replace(':', '').lower()
