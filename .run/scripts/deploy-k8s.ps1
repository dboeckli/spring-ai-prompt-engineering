cd target/helm/repo

$file = Get-ChildItem -Filter spring-ai-prompt-engineering-v*.tgz | Select-Object -First 1
tar -xvf $file.Name

$APPLICATION_NAME = Get-ChildItem -Directory |
    Where-Object { $_.LastWriteTime -ge $file.LastWriteTime } |
    Select-Object -ExpandProperty Name

# Prompt for required values
$DEEPSEEK_API_KEY = Read-Host "Enter your OpenAI API Key (DEEPSEEK_API_KEY)" -AsSecureString
$DOCKER_USER = "domboeckli"
$DOCKER_TOKEN = Read-Host "Enter your Docker token (DOCKER_TOKEN)" -AsSecureString

# Convert SecureString to plain text for use in command
$DEEPSEEK_API_KEY_PLAIN = [Runtime.InteropServices.Marshal]::PtrToStringAuto([Runtime.InteropServices.Marshal]::SecureStringToBSTR($DEEPSEEK_API_KEY))
$DOCKER_TOKEN_PLAIN = [Runtime.InteropServices.Marshal]::PtrToStringAuto([Runtime.InteropServices.Marshal]::SecureStringToBSTR($DOCKER_TOKEN))


helm upgrade --install $APPLICATION_NAME ./$APPLICATION_NAME `
    --namespace spring-ai-prompt-engineering `
    --create-namespace `
    --wait `
    --timeout 8m `
    --debug `
    --render-subchart-notes `
    --set openai.apiKey="$DEEPSEEK_API_KEY_PLAIN" `
    --set docker.dockerUser="$DOCKER_USER" `
    --set docker.dockerToken="$DOCKER_TOKEN_PLAIN"