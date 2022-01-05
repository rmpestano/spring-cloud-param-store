#!/bin/bash

aws --endpoint-url=http://localhost:4566 ssm put-parameter --name '/config/demo/demo.param' --type String --value 'test' --overwrite --region 'eu-west-1'
