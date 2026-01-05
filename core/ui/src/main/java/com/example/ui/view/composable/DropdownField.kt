package com.example.ui.view.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.themes.TextFieldHeight

@Composable
fun <T> DropdownField(
    onValueChange: (T) -> Unit,
    label: String,
    options: List<T>,
    isLoading: Boolean,
    enabled: Boolean,
    getDisplayText: (T) -> String,
    displayText: String,
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onFieldClick: () -> Unit,
    placeholder: String? = null,
    contentDescription: String? = null
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Box {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(TextFieldHeight)
                    .clickable(
                        enabled = enabled && !isLoading,
                        onClickLabel = contentDescription
                    ) {
                        if (options.isEmpty() && !isLoading && enabled && !expanded) {
                            onFieldClick()
                        } else if (options.isNotEmpty()) {
                            onExpandedChange(!expanded)
                        }
                    },
            shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(TextFieldHeight)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = when {
                            displayText.isEmpty() && isLoading -> stringResource(R.string.please_wait)
                            displayText.isEmpty() && placeholder != null -> placeholder
                            else -> displayText
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (displayText.isEmpty() && placeholder != null) {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        },
                        modifier = Modifier.weight(1f)
                    )
                    if (options.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            }
            if (options.isNotEmpty()) {
                DropdownMenu(
            expanded = expanded,
                    onDismissRequest = { onExpandedChange(false) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 300.dp)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(getDisplayText(option)) },
                    onClick = {
                        onValueChange(option)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}
    }
}
