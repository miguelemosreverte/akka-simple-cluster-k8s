minikube start
eval $(minikube docker-env)
sbt docker:publishLocal
# create serviceAccount and role
kubectl create -f k8s/simple-akka-cluster-rbac.yml --validate=false
# create deployment
kubectl create -f k8s/simple-akka-cluster-deployment.yml --validate=false
# create service
kubectl create -f k8s/simple-akka-cluster-service.yml --validate=false
KUBE_IP=$(minikube ip)
MANAGEMENT_PORT=$(kubectl get svc akka-simple-cluster -ojsonpath="{.spec.ports[?(@.name==\"management\")].nodePort}")
curl http://$KUBE_IP:$MANAGEMENT_PORT/cluster/members | jq