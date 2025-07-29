# Einmalig installieren
# Install-Module -Name CredentialManager -Scope CurrentUser

# Den DEEPSEEK_API_KEY in der Windows Anmeldeinformation als Generische Anmeldeinformation setzen:
# - Internet oder Netzwerkadresse: DEEPSEEK_API_KEY
# - Benutzername: DEEPSEEK_API_KEY
# - Kennwort: key aus dem keypass

$cred = Get-StoredCredential -Target 'DEEPSEEK_API_KEY'

if ($cred -and $cred.Password) {
    $token = [System.Runtime.InteropServices.Marshal]::PtrToStringAuto(
        [System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($cred.Password)
    )
    $env:DEEPSEEK_API_KEY = $token
    Write-Host "Token erfolgreich in Env-Var gesetzt."

    Set-Content -Path "..\.deepseekapi-key-env" -Value "DEEPSEEK_API_KEY=$token"
    Write-Host "Env .deepseekapi-key-env file written."
} else {
    Write-Warning "Credential 'DEEPSEEK_API_KEY' nicht gefunden oder leer."
}