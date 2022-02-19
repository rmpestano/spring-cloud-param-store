#!/bin/bash
aws configure set cli_follow_urlparam false
awslocal --endpoint-url=http://localhost:4566 ssm put-parameter --name '/config/demo/demo.param' --type String --value 'from param store' --overwrite --region 'eu-west-1'
