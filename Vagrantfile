# Specify Vagrant version and Vagrant API version
# run this file vagrant up --provider=docker
# will look for dockerfile in the same directory with the vagrantfile
#Vagrant.require_version ">= 1.6.0"

VAGRANTFILE_API_VERSION = "2"
DOCKER_HOST_VAGRANTFILE = "./DockerHostVagrantfile"
VAGRANT_MACHINE_NAME = "dockerhostvm"
MYSQL_DATABASE = "allianz"
MYSQL_USER = "allianz"
MYSQL_ROOT_PASSWORD = "root"
MYSQL_PASSWORD = "allianz"
MYSQL_PORT=3306
TOMCAT_PORT=8888


ENV['VAGRANT_DEFAULT_PROVIDER'] = 'docker'

Vagrant.configure("2") do |config|
#config.vm.network "forwarded_port", guest: 8888, host: 2201, auto_correct: true
# Configure VM Ram usage
config.vm.provider :virtualbox do |vb|
  vb.customize ["modifyvm", :id, "--memory", "1024"]
end
#1. Data container
config.vm.define "dataContainer" do |data|
	data.vm.provider :docker do |d|
		d.name = 'dataContainer'
		d.build_dir = "makedata"
		d.remains_running = true
		d.vagrant_machine = "#{VAGRANT_MACHINE_NAME}"
		d.vagrant_vagrantfile = "#{DOCKER_HOST_VAGRANTFILE}"
		d.volumes = ["/host_data/:/usr/local/tomcat/logs/"] 
		d.create_args = ["-d", "-it"]
	end
end
#2. MySQL container
config.vm.define "mysqlContainer" do |mysql|
	mysql.vm.provider :docker do |d|
		d.name = 'mysqlContainer'
		d.build_dir = "makemysql"
		d.remains_running = true
		d.vagrant_machine = "#{VAGRANT_MACHINE_NAME}"
		d.vagrant_vagrantfile = "#{DOCKER_HOST_VAGRANTFILE}"
		d.ports = ["#{MYSQL_PORT}:3306"]
		d.link("dataContainer:data")
		d.create_args = ["-dit", "--volumes-from=dataContainer", 
						"-e MYSQL_ROOT_PASSWORD=#{MYSQL_ROOT_PASSWORD}", 
						"-e MYSQL_DATABASE=#{MYSQL_DATABASE}", 
						"-e MYSQL_USER=#{MYSQL_USER}", 
						"-e MYSQL_PASSWORD=#{MYSQL_PASSWORD}"]
	end
end
#3. Application container
config.vm.define "applicationContainer" do |m|
	m.vm.synced_folder "c:/data2", "/usr/local/tomcat/logs/"
	m.vm.provider :docker do |d|
		d.name = 'applicationContainer'
		d.build_dir = "makeapp"
		d.remains_running = true
		d.vagrant_machine = "#{VAGRANT_MACHINE_NAME}"
		d.vagrant_vagrantfile = "#{DOCKER_HOST_VAGRANTFILE}"
		d.ports = ["#{TOMCAT_PORT}:8080"]
		d.link("mysqlContainer:mysql")
		d.link("dataContainer:logs")		
		d.build_args = ["--build-arg=PORT=#{MYSQL_PORT}", 
						'--build-arg=HOST=mysql', 
						"--build-arg=DATABASE=#{MYSQL_DATABASE}", 
						"--build-arg=USERNAME=#{MYSQL_USER}", 
						"--build-arg=PASSWORD=#{MYSQL_PASSWORD}"]
		d.create_args = ["-dit", "--memory=1024M",
						"--volumes-from=dataContainer"]
	end
end
end