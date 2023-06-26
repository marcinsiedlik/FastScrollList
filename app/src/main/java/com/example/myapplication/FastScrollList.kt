package com.example.myapplication

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.text.Collator

/*
  TODO's:
  - chars column width
 */
@Composable
fun <T> FastScrollList(
  items: List<T>,
  key: (T) -> Char,
  modifier: Modifier = Modifier,
  charContent: @Composable (char: Char, requestFocus: () -> Unit) -> Unit,
  itemContent: @Composable (item: T) -> Unit,
) {
  val coroutineScope = rememberCoroutineScope()
  val itemsListState = rememberLazyListState()
  val charsListState = rememberLazyListState()
  var lettersAutoScrollBlocked by remember { mutableStateOf(false) }

  val sortedItems = remember(items) {
    items.sortedWith(Collator.getInstance())
  }
  val chars = remember(sortedItems, key) {
    sortedItems.map(key).map(Char::titlecaseChar).distinct()
  }

  val firstVisibleIndex by remember {
    derivedStateOf { itemsListState.firstVisibleItemIndex }
  }
  val firstVisibleItem by remember {
    derivedStateOf { chars.indexOfFirst { sortedItems[firstVisibleIndex].let(key).titlecaseChar() == it } }
  }

  val requestFocusCallback = remember(sortedItems) {
    { item: Char ->
      val firstItemIndex = sortedItems.indexOfFirst { key(it).titlecaseChar() == item }
      coroutineScope.launch {
        lettersAutoScrollBlocked = true
        itemsListState.animateScrollToItem(firstItemIndex)
        lettersAutoScrollBlocked = false
      }
    }
  }

  LaunchedEffect(firstVisibleItem) {
    if (lettersAutoScrollBlocked) return@LaunchedEffect
    charsListState.animateScrollToItem(firstVisibleItem)
  }

  var widestCharCell by remember { mutableStateOf(0.dp) }
  Layout(content = { chars.forEach { charContent(char = it, requestFocus = { }) } })
  { measurables, constraints ->
    val placeables = measurables.map { it.measure(constraints) }
    widestCharCell = placeables.maxOf { it.width }.toDp()
    layout(0, 0) {}
  }

  Row(modifier = modifier) {
    LazyColumn(
      modifier = Modifier.weight(1f),
      state = itemsListState,
    ) {
      items(sortedItems) { item ->
        itemContent(item)
      }
    }
    LazyColumn(
      modifier = Modifier
        .align(Alignment.CenterVertically)
        .width(widestCharCell),
      state = charsListState,
    ) {
      items(chars) { item ->
        charContent(
          char = item,
          requestFocus = { requestFocusCallback(item) },
        )
      }
    }
  }
}
