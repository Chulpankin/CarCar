package com.example.ui.view.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.ui.themes.Shapes
import com.example.ui.view.composable.ShimmerBox

@Composable
fun CarListItemSkeleton(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = Shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                ShimmerBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp)),
                    height = 24.dp
                )

                ShimmerBox(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .clip(RoundedCornerShape(4.dp)),
                    height = 20.dp
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ShimmerBox(
                        modifier = Modifier
                            .width(80.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        height = 18.dp
                    )
                    ShimmerBox(
                        modifier = Modifier
                            .width(60.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        height = 18.dp
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(top = 2.dp)
                ) {
                    ShimmerBox(
                        modifier = Modifier
                            .width(70.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        height = 18.dp
                    )
                    ShimmerBox(
                        modifier = Modifier
                            .width(60.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        height = 18.dp
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 2.dp)
                ) {
                    ShimmerBox(
                        modifier = Modifier
                            .width(100.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        height = 16.dp
                    )
                    ShimmerBox(
                        modifier = Modifier
                            .width(80.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        height = 16.dp
                    )
                    ShimmerBox(
                        modifier = Modifier
                            .width(90.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        height = 16.dp
                    )
                }
            }

            Box(
                modifier = Modifier.align(Alignment.Top),
                contentAlignment = Alignment.Center
            ) {
                ShimmerBox(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape),
                    height = 24.dp
                )
            }
        }
    }
}

