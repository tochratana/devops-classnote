#!/bin/bash
set -e

echo "Starting deployment of microservices infrastructure..."

# 1. Create the namespace
echo "Creating namespace..."
kubectl apply -f menual-test/namespace.yaml

# 2. Convert the local configuration files to a Kubernetes ConfigMap
# This replaces the Git URI for the config-server, so it runs entirely from our local state
echo "Creating ConfigMap from spring-micro-breakdowns-config-repo/config..."
kubectl create configmap microservice-config \
  --from-file=spring-micro-breakdowns-config-repo/config/ \
  -n microservices \
  --dry-run=client -o yaml | kubectl apply -f -

# 3. Apply the database manifests
echo "Deploying MongoDB..."
kubectl apply -f menual-test/database/

# 4. Apply the configuration service
echo "Deploying Config Service..."
kubectl apply -f menual-test/config-service/

# 5. Apply core microservices
echo "Deploying User and Product services..."
kubectl apply -f menual-test/product-service/
kubectl apply -f menual-test/user-service/

# 6. Apply the API Gateway
echo "Deploying API Gateway..."
kubectl apply -f menual-test/api-geteway/

echo "Deployment complete!"
echo "Check the status using: kubectl get pods -n microservices"
