package com.blueprint.composecrossdrill.ui.features.dashboard.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.blueprint.composecrossdrill.domain.model.User

@Composable
fun UserCard(user: User) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Name and Username
            Text(
                text = user.name ?: "No Name",
            )
            Text(
                text = "@${user.username ?: "No Username"}",
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Email
            Text(
                text = "Email: ${user.email ?: "No Email"}",
            )

            // Phone
            Text(
                text = "Phone: ${user.phone ?: "No Phone"}",
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Address
            user.address?.let {
                Text(
                    text = "Address: ${it.street}, ${it.city}, ${it.zipcode}",
                )
            }

            // Company
            user.company?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Company: ${it.name}",
                )
            }
        }
    }
}
