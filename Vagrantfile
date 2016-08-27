# Specify Vagrant version and Vagrant API version
# run this file vagrant up --provider=docker
# will look for dockerfile in the same directory with the vagrantfile
#Vagrant.require_version ">= 1.6.0"

require 'yaml'

settings = YAML.load_file(File.join(File.dirname(__FILE__), 'VagrantConfig.yml'))

db_root_password = String.new(settings['db']['root_password'])
db_name = String.new(settings['db']['db_name'])
db_user_name = String.new(settings['db']['db_user_name'])
db_password = String.new(settings['db']['db_password'])
mysql_port = settings['port']['mysql_port']
tomcat_port = settings['port']['tomcat_port']
forwarded_port = settings['port']['forwarded_port']
app_database = String.new(settings['application']['app_database'])
app_password = String.new(settings['application']['app_password'])
app_user_name = String.new(settings['application']['app_user_name'])
machine_name = String.new(settings['vagrant']['machine_name'])


VAGRANTFILE_API_VERSION = "2"
DOCKER_HOST_VAGRANTFILE = "./DockerHostVagrantfile"
#machine_name = "dockerhostvm"


ENV['VAGRANT_DEFAULT_PROVIDER'] = 'docker'

Vagrant.configure("2") do |config|
config.vm.network "forwarded_port", guest: "#{tomcat_port}", host: "#{forwarded_port}", auto_correct: true
# Configure VM Ram usage
config.vm.provider :virtualbox do |vb|
  vb.customize "pre-boot", ["modifyvm", :id, "--resize", "2048"]
end
#1. Data container
config.vm.define "dataContainer" do |data|
	data.vm.provider :docker do |d|
		d.name = 'dataContainer'
		d.build_dir = "makedata"
		d.remains_running = true
		d.vagrant_machine = "#{machine_name}"
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
		d.vagrant_machine = "#{machine_name}"
		d.vagrant_vagrantfile = "#{DOCKER_HOST_VAGRANTFILE}"
		d.ports = ["#{mysql_port}:3306"]
		d.link("dataContainer:data")
		d.create_args = ["-it", "--volumes-from=dataContainer", 
						"-e", "#{db_root_password}",
						"-e", "#{db_name}", 
						"-e", "#{db_user_name}", 
						"-e", "#{db_password}"]
	end
end
#3. Application container
config.vm.define "applicationContainer" do |m|
	m.vm.synced_folder "c:/data2", "/usr/local/tomcat/logs/"
	m.vm.provider :docker do |d|
		d.name = 'applicationContainer'
		d.build_dir = "makeapp"
		d.remains_running = true
		d.vagrant_machine = "#{machine_name}"
		d.vagrant_vagrantfile = "#{DOCKER_HOST_VAGRANTFILE}"
		d.ports = ["#{tomcat_port}:8080"]
		d.link("mysqlContainer:mysql")
		d.link("dataContainer:logs")		
		d.build_args = ["--build-arg=PORT=#{mysql_port}", 
						'--build-arg=HOST=mysql', 
						"--build-arg=DATABASE=#{app_database}", 
						"--build-arg=USERNAME=#{app_user_name}", 
						"--build-arg=PASSWORD=#{app_password}"]
		d.create_args = ["-dit", "--memory=1024M",
						"--volumes-from=dataContainer"]
	end
end
end