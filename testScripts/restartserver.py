import paramiko

ssh = paramiko.SSHClient()
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
ip = "192.168.0.5"
try:
  ssh.connect(hostname=ip, username='root', password='Teamsvmware01!', port=3389)
  print("Connected")
except Exception as error:
  print("Connection failed")

try:
  stdin, stdout, stderr = ssh.exec_command("systemctl reboot")
  print("Restarting " + ip)
except Exception as error:
  print("Test failed: command failed")

ssh.close()
print("Test succesfull: server rebooted")