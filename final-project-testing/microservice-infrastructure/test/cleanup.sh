#!/bin/bash
set -e

echo "Starting cleanup of microservices infrastructure..."

# 1. Delete API Gateway
echo "Deleting API Gateway..."
kubectl delete -f menual-test/api-geteway/ --ignore-not-found=true

# 2. Delete core microservices
echo "Deleting User and Product services..."
kubectl delete -f menual-test/user-service/ --ignore-not-found=true
kubectl delete -f menual-test/product-service/ --ignore-not-found=true

# 3. Delete Config Service
echo "Deleting Config Service..."
kubectl delete -f menual-test/config-service/ --ignore-not-found=true

# 4. Delete Database
echo "Deleting MongoDB..."
kubectl delete -f menual-test/database/ --ignore-not-found=true

# 5. Delete ConfigMap
echo "Deleting ConfigMap..."
kubectl delete configmap microservice-config -n microservices --ignore-not-found=true

# 6. Delete Namespace
echo "Deleting namespace..."
kubectl delete -f menual-test/namespace.yaml --ignore-not-found=true

echo "Cleanup complete!"