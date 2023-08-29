package com.doctoror.splittor.ui.addgroup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.doctoror.splittor.presentation.addgroup.ADD_GROUP_VIEW_MODEL_KEY_AMOUNT
import com.doctoror.splittor.presentation.addgroup.ADD_GROUP_VIEW_MODEL_KEY_CONTACTS
import com.doctoror.splittor.presentation.addgroup.ADD_GROUP_VIEW_MODEL_KEY_TITLE
import com.doctoror.splittor.presentation.addgroup.AddGroupViewModel
import com.doctoror.splittor.presentation.addgroup.ContactDetailsViewModel
import com.doctoror.splittor.ui.R
import com.doctoror.splittor.ui.base.AppTheme
import me.thekusch.composeview.CurrencyTextField
import java.util.Locale

@ExperimentalMaterial3Api
@Composable
fun AddGroupContent(
    currencySymbol: String,
    locale: Locale,
    onAmountChange: (String) -> Unit,
    onAddContactClick: () -> Unit,
    onCreateClick: () -> Unit,
    onNavigationClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    viewModel: AddGroupViewModel,
) {

    AppTheme {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = onCreateClick) {
                    Image(
                        imageVector = Icons.Filled.Check,
                        contentDescription = stringResource(id = R.string.create)
                    )
                }
            },
            topBar = {
                LargeTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = onNavigationClick) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                stringResource(R.string.go_back)
                            )
                        }
                    },
                    title = { Text(stringResource(R.string.add_new_group)) }
                )
            }
        ) {
            Column(Modifier.padding(it)) {
                GroupInfoSection(
                    currencySymbol = currencySymbol,
                    onAmountChange = onAmountChange,
                    onTitleChange = onTitleChange,
                    locale = locale,
                    viewModel = viewModel
                )

                ContactsSection(
                    onAddContactClick = onAddContactClick,
                    viewModel = viewModel
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun GroupInfoSection(
    currencySymbol: String,
    locale: Locale,
    onAmountChange: (String) -> Unit,
    onTitleChange: (String) -> Unit,
    viewModel: AddGroupViewModel,
) {
    val focusManager = LocalFocusManager.current

    val amount = viewModel.amount.collectAsStateWithLifecycle()
    val title = viewModel.title.collectAsStateWithLifecycle()

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 24.dp,
                end = 16.dp
            ),
        color = AppTheme.colorScheme.primary,
        style = AppTheme.typography.labelLarge,
        text = stringResource(R.string.group_info).uppercase()
    )

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 8.dp,
                end = 16.dp
            ),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Next) }
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Next
        ),
        label = { Text(text = stringResource(R.string.title)) },
        singleLine = true,
        value = title.value,
        onValueChange = onTitleChange
    )

    CurrencyTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp),
        currencySymbol = currencySymbol,
        initialText = amount.value,
        label = { Text(text = stringResource(R.string.amount)) },
        locale = locale,
        onChange = onAmountChange
    )

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 24.dp,
                end = 16.dp
            ),
        color = AppTheme.colorScheme.primary,
        style = AppTheme.typography.labelLarge,
        text = stringResource(R.string.contacts).uppercase()
    )
}

@ExperimentalMaterial3Api
@Composable
private fun ContactsSection(
    onAddContactClick: () -> Unit,
    viewModel: AddGroupViewModel
) {
    val contacts = viewModel.contacts.collectAsStateWithLifecycle()

    contacts.value.forEach {
        Box(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(Alignment.CenterVertically)
                .defaultMinSize(minHeight = 56.dp)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                color = AppTheme.colorScheme.onSurface,
                style = AppTheme.typography.bodyLarge,
                text = it.name
            )
        }
    }

    Box(Modifier.fillMaxWidth()) {

        Button(
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight(Alignment.CenterVertically)
                .defaultMinSize(minWidth = 140.dp, minHeight = 56.dp)
                .align(Alignment.Center)
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 32.dp),
            onClick = onAddContactClick
        ) {

            Text(text = stringResource(R.string.add_contact))
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun AddGroupContentPreview() {
    AddGroupContent(
        currencySymbol = "$",
        locale = Locale.US,
        onAmountChange = {},
        onAddContactClick = { },
        onCreateClick = {},
        onNavigationClick = {},
        onTitleChange = {},
        viewModel = AddGroupViewModel(
            SavedStateHandle(
                mapOf(
                    ADD_GROUP_VIEW_MODEL_KEY_AMOUNT to "1,099.29",
                    ADD_GROUP_VIEW_MODEL_KEY_CONTACTS to listOf(
                        ContactDetailsViewModel(
                            id = 1L,
                            name = "Alice"
                        ),
                        ContactDetailsViewModel(
                            id = 2L,
                            name = "Bob"
                        )
                    ),
                    ADD_GROUP_VIEW_MODEL_KEY_TITLE to "Dinner"
                )
            )
        )
    )
}
