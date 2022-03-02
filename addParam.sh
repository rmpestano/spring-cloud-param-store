#!/bin/bash
export AWS_ACCESS_KEY_ID=test
export AWS_SECRET_ACCESS_KEY=test
aws --endpoint-url=http://localhost:4566 ssm put-parameter --name "/config/demo/$1" --type String --value "$2" --overwrite --region 'us-east-1'
aws --endpoint-url=http://localhost:4566 ssm get-parameters-by-path --recursive --with-decryption --path /config/demo/ --region 'us-east-1'