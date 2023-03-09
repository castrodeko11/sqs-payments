# sqs-payments

# Create-queue
aws sqs create-queue --endpoint-url http://localhost:4566 --queue-name tax-payments --profile localstack

# List-queues
aws sqs list-queues --endpoint-url http://localhost:4566 --profile localstack

# Send-message
aws sqs send-message --endpoint-url http://localhost:4566 --queue-url http://localhost:4566/000000000000/tax-payments --message-body "Mensagem de Teste" --message-attributes file://./message.json --profile localstack

# Read Message
#Note -> run command in directory with file ${message.json}

aws sqs receive-message --endpoint-url http://localhost:4566 --queue-url http://localhost:4566/000000000000/teste --attribute-names All --message-attribute-names All  --profile localstack
