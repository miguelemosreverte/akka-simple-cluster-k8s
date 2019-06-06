#Installing VirtualBox
echo "Installing VirtualBox........................"
sudo apt-get install virtualbox

#Installing kubectl https://kubernetes.io/docs/getting-started-guides/kubectl/
curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl
chmod +x ./kubectl
sudo mv ./kubectl /usr/local/bin/kubectl


#Installing minikube https://github.com/kubernetes/minikube/releases
echo "Installing minikube.........................."
curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64 && chmod +x minikube && sudo mv minikube /usr/local/bin/

#jq to see pretty json on
sudo apt-get install jq

#java
sudo apt update  
sudo apt install oracle-java8-installer  
sudo apt install oracle-java8-set-default  
#scala
sudo apt-get remove scala-library scala  
sudo apt-get update  
cd ~/Downloads  
sudo wget www.scala-lang.org/files/archive/scala-2.12.6.deb  
sudo dpkg -i scala-2.12.6.deb  
#sbt
sudo apt-get update  
echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list  
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823  
sudo apt-get update  
sudo apt-get install sbt  