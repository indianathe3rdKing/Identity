package com.indianathe3rd.identity.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.indianathe3rd.identity.presentation.screen.home.HomeScreen
import com.indianathe3rd.identity.presentation.screen.profile.ProfileScreen
import com.indianathe3rd.identity.presentation.ui.icons.Icons

@Composable
fun AppNavigation() {
    var selectTabIndex by remember{
        mutableStateOf(0)
    }

    Scaffold(
        containerColor = Color.Transparent,
        content = {
            innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ){
                when(selectTabIndex){
                    0->HomeScreen()
                    1->ProfileScreen()
                }
            }
        },
        bottomBar = {

            Surface(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                color = MaterialTheme.colorScheme.background) {
                NavigationBar(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onBackground

                ) {
                    TabNavigationItem(
                        title = "Home",
                        icon = Icons.Home,
                        selected = selectTabIndex ==0,
                        onClick = {selectTabIndex=0}
                    )
                    TabNavigationItem(
                        title = "Profile",
                        icon = Icons.Cog6Tooth,
                        selected = selectTabIndex==1,
                        onClick = {selectTabIndex=1}
                    )
                }
            }
        }
    )
}

@Composable
private fun RowScope.TabNavigationItem(
    title: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
){
    NavigationBarItem(
        selected = selected
        ,onClick = onClick,
        icon = {
            Icon(imageVector = icon,
                contentDescription = title)
        },
        label = {Text(title)},
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.onBackground,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedTextColor = MaterialTheme.colorScheme.onBackground
        )
    )
}