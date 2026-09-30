package com.company.jetpackbottombarexample.auth.authScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.company.jetpackbottombarexample.auth.viewModel.SignUpViewModel

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    val viewModel: SignUpViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        // Header
        Text(
            text = "Create Account",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 32.dp, bottom = 24.dp)
        )

        // Error message
        viewModel.errorMessage.collectAsState().value?.let { error ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        // Name field
        OutlinedTextField(
            value = viewModel.name.collectAsState().value,
            onValueChange = viewModel::updateName,
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            isError = viewModel.validationErrors.collectAsState().value.containsKey("name"),
            supportingText = {
                viewModel.validationErrors.collectAsState().value["name"]?.let { error ->
                    Text(text = error, color = MaterialTheme.colorScheme.error)
                }
            },
            leadingIcon = {
                Icon(Icons.Filled.Person, contentDescription = "Name")
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Email field
        OutlinedTextField(
            value = viewModel.email.collectAsState().value,
            onValueChange = viewModel::updateEmail,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            isError = viewModel.validationErrors.collectAsState().value.containsKey("email"),
            supportingText = {
                viewModel.validationErrors.collectAsState().value["email"]?.let { error ->
                    Text(text = error, color = MaterialTheme.colorScheme.error)
                }
            },
            leadingIcon = {
                Icon(Icons.Filled.Email, contentDescription = "Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )
        Spacer(modifier = Modifier.height(12.dp))

        // Password field
        var isPasswordVisible by remember { mutableStateOf(false) }


        OutlinedTextField(
            value = viewModel.password.collectAsState().value,
            onValueChange = viewModel::updatePassword,
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            isError = viewModel.validationErrors.collectAsState().value.containsKey("password"),
            supportingText = {
                viewModel.validationErrors.value["password"]?.let { error ->
                    Text(text = error, color = MaterialTheme.colorScheme.error)
                } ?: Text("Minimum 6 characters")
            },
            leadingIcon = {
                Icon(Icons.Filled.Lock, contentDescription = "Password")
            },
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                    )
                }
            },
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Confirm Password field
        var isConfirmPasswordVisible by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = viewModel.confirmPassword.collectAsState().value,
            onValueChange = viewModel::updateConfirmPassword,
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth(),
            isError = viewModel.validationErrors.collectAsState().value.containsKey("confirmPassword"),
            supportingText = {
                viewModel.validationErrors.collectAsState().value["confirmPassword"]?.let { error ->
                    Text(text = error, color = MaterialTheme.colorScheme.error)
                }
            },
            leadingIcon = {
                Icon(Icons.Filled.Lock, contentDescription = "Confirm Password")
            },
            trailingIcon = {
                IconButton(onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible }) {
                    Icon(
                        imageVector = if (isConfirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (isConfirmPasswordVisible) "Hide password" else "Show password"
                    )
                }
            },
            visualTransformation = if (isConfirmPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )

        // Terms and Conditions
        var isTermsAccepted by remember { mutableStateOf(true) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isTermsAccepted,
                onCheckedChange = { isTermsAccepted = it }
            )
            Text(
                text = "I agree to the ",
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                text = "Terms & Conditions",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    // Navigate to terms screen
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sign Up Button
        Button(
            onClick = viewModel::signUp,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
//            enabled = !viewModel.isLoading.value && isTermsAccepted && viewModel.isFormValid.value
        ) {
            if (viewModel.isLoading.collectAsState().value) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

    }
}