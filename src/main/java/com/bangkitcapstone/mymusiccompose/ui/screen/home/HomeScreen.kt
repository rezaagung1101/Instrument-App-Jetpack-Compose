package com.bangkitcapstone.mymusiccompose.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkitcapstone.mymusiccompose.di.Injection
import com.bangkitcapstone.mymusiccompose.model.OrderInstrument
import com.bangkitcapstone.mymusiccompose.ui.ViewModelFactory
import com.bangkitcapstone.mymusiccompose.ui.common.UiState
import com.bangkitcapstone.mymusiccompose.ui.component.InstrumentItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllInstrument()
            }
            is UiState.Success -> {
                HomeContent(
                    orderInstrument = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    orderInstrument: List<OrderInstrument>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.testTag("InstrumentList")
    ) {
        items(orderInstrument) { data ->
            InstrumentItem(
                image = data.instrument.image,
                title = data.instrument.name,
                price = data.instrument.price,
                modifier = Modifier.clickable {
                    navigateToDetail(data.instrument.id)
                }
            )
        }
    }
}