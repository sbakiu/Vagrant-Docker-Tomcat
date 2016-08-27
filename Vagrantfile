# Specify Vagrant version and Vagrant API version
# run this file vagrant up --provider=docker
# will look for dockerfile in the same directory with the vagrantfile
#Vagrant.require_version ">= 1.6.0"

require 'yaml'
# Load settings file
settings = YAML.load_file(File.join(File.dirname(__FILE__), 'VagrantConfig.yml'))

# Database settings
db_root_password = settings['mysql']['root_password'] + "=" + settings['db']['root_password']
db_name = settings['mysql']['db_name'] + "=" + settings['db']['db_name']
db_user_name = settings['mysql']['db_user_name'] + "=" + settings['db']['db_user_name']
db_password = settings['mysql']['db_password'] + "=" + settings['db']['db_password']

#Port settings
mysql_port = settings['port']['mysql_port']
tomcat_port = settings['port']['tomcat_port']
forwarded_port = settings['port']['forwarded_port']

#Application database connection settings
app_database = String.new(settings['db']['db_name'])
app_password = String.new(settings['db']['db_password'])
app_user_name = String.new(settings['db']['db_user_name'])

#Vagrant settings
machine_name = String.new(settings['vagrant']['machine_name'])
api_version = settings['vagrant']['api_version']
default_provider = settings['vagrant']['default_provider']

VAGRANTFILE_API_VERSION = "#{api_version}"
DOCKER_HOST_VAGRANTFILE = "./DockerHostVagrantfile"

ENV['VAGRANT_DEFAULT_PROVIDER'] = "#{default_provider}"

Vagrant.configure("2") do |config|
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
	m.vm.provider :docker do |d|
		d.name = 'applicationContainer'
		d.build_dir = "makeapp"
		d.remains_running = true
		d.vagrant_machine = "#{machine_name}"
		d.vagrant_vagrantfile = "#{DOCKER_HOST_VAGRANTFILE}"
		d.ports = ["#{tomcat_port}:8080"]
		d.link("mysqlContainer:mysql")		
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