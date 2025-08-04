# Introduction to Spring AI

This repository contains source code examples for Spring AI.
This project uses private Docker repositories and GitHub, requiring specific deployment configurations for secure access and management.

Application runs on port 8080/30080

## AI Test Prompts

A collection of AI test prompts can be found in the [AI-Test.md](AI-Test.md) file. These prompts were developed by Mathew Berman to test and evaluate the capabilities of different
AI models.

## 1. OpenAI Platform and Monitoring

### 1.1 Important URLs

- Main Platform: https://platform.deepseek.com/
- API Usage and Consumption: https://platform.deepseek.com/account/usage
- Billing Details: https://platform.deepseek.com/account/billing
- API Keys: https://platform.deepseek.com/api_keys

Off-Peak Discounts：DeepSeek-V3 with 50% off and DeepSeek-R1 with 75% off at off-peak hours (16:30-00:30 UTC daily).
Check here: https://api-docs.deepseek.com/quick_start/pricing/

### 1.2 Monitoring Information

#### Usage Dashboard

- Current consumption
- Historical usage
- Model-specific breakdown
- Cost estimates

#### Billing Dashboard

- Current balance
- Payment history
- Subscription details
- Usage limits

### 1.3 API Key Configuration

The Openai key (DEEPSEEK_API_KEY) has been placed as environment variable in:

* Junit
* Maven
* Spring Boot Runner and Unit Tests: run PowerShell script set-openapi-key-as-env.ps1.run.xml via intellij run config.
  This script sets the environment variables by creating and using the file:
  File: [.run/.deepseekapi-key-env](.run/.deepseekapi-key-env)

For local development you need to set:
Set the DEEPSEEK_API_KEY in Windows Credentials as Generic Credential:

- Internet or network address: DEEPSEEK_API_KEY
- Username: DEEPSEEK_API_KEY
- Password: key from keypass

## Deployment with Helm

Be aware that we are using a different namespace here (not default).

To run maven filtering for destination target/helm

```bash
mvn clean install -DskipTests 
```

Go to the directory where the tgz file has been created after 'mvn install'

```powershell
cd target/helm/repo
```

unpack

```powershell
$file = Get-ChildItem -Filter *.tgz | Select-Object -First 1
tar -xvf $file.Name
```

install

```powershell
$APPLICATION_NAME = Get-ChildItem -Directory | Where-Object { $_.LastWriteTime -ge $file.LastWriteTime } | Select-Object -ExpandProperty Name
helm upgrade --install $APPLICATION_NAME ./$APPLICATION_NAME --namespace spring-ai-prompt-engineering --create-namespace --wait --timeout 8m --debug --render-subchart-notes
```

show logs

```powershell
kubectl get pods -l app.kubernetes.io/name=$APPLICATION_NAME -n spring-ai-prompt-engineering
```

replace $POD with pods from the command above

```powershell
kubectl logs $POD -n spring-ai-prompt-engineering --all-containers
```

test

```powershell
helm test $APPLICATION_NAME --namespace spring-ai-prompt-engineering --logs
```

uninstall

```powershell
helm uninstall $APPLICATION_NAME --namespace spring-ai-prompt-engineering
```

delete all

```powershell
kubectl delete all --all -n spring-ai-prompt-engineering
```

create busybox sidecar

```powershell
kubectl run busybox-test --rm -it --image=busybox:1.36 --namespace=spring-ai-prompt-engineering --command -- sh
```

You can use the actuator rest call to verify via port 30080
