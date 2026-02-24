package ru.otus.compose.customlayout

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import kotlin.math.ceil


/**
 * Task: Make custom grid layout
 */
@Composable
fun CustomLayoutHW(
    columns: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { }
) {
    // Replace Column with your custom layout
    Layout (
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        if (columns > 0 && measurables.isNotEmpty()) {
            val rows = ceil(measurables.count().toDouble() / columns).toInt()
            val width = if (constraints.hasBoundedWidth) {
                constraints.maxWidth
            } else {
                Resources.getSystem().displayMetrics.widthPixels
            }
            val maxPlaceableWidth = width / columns
            val maxPlaceableHeight = constraints.maxHeight / rows

            val placeables = measurables.map { measurable ->
                measurable.measure(
                    Constraints(
                        maxWidth = maxPlaceableWidth,
                        maxHeight = maxPlaceableHeight
                    )
                )
            }

            val rowsInLists = placeables.chunked(columns)
            val maxColumnWidths = IntArray(columns) { col ->
                rowsInLists.maxOfOrNull { row -> row.getOrNull(col)?.width ?: 0 } ?: 0
            }
            val maxRowHeights = rowsInLists.map { row -> row.maxOfOrNull { it.height } ?: 0 }

            val w = constraints.constrainWidth(maxColumnWidths.sum())
            val h = constraints.constrainHeight(maxRowHeights.sum())
            layout(w, h){
                var yOffset = 0
                rowsInLists.forEachIndexed { rowIndex, row ->
                    val rowHeight = maxRowHeights[rowIndex]
                    var xOffset = 0
                    row.forEachIndexed { columnIndex, placeable ->
                        placeable.placeRelative(x = xOffset, y = yOffset)
                        xOffset += maxColumnWidths[columnIndex]
                    }
                    yOffset += rowHeight
                }
            }
        } else {
            layout(constraints.minWidth, constraints.minHeight) {}
        }
    }
}

@Preview
@Composable
fun CustomLayoutHWPreview() {
    Surface {
        CustomLayoutHW(
            columns = 3,
            modifier = Modifier
                .padding(4.dp)
                .border(2.dp, color = Color.Black)
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(100.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(110.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(90.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(120.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(100.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(80.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(100.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(120.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(100.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(90.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
        }
    }
}