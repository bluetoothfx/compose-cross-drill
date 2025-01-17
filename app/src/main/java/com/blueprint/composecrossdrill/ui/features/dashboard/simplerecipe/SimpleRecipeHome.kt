package com.blueprint.composecrossdrill.ui.features.dashboard.simplerecipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.blueprint.composecrossdrill.R
import com.blueprint.composecrossdrill.domain.model.category.RecipeCategory
import com.blueprint.composecrossdrill.domain.model.recipes.Recipe
import com.blueprint.composecrossdrill.ui.features.dashboard.viewmodel.DashboardViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun TopAppBar() {
    Row(Modifier.padding(top = 0.dp)) {
        Column(
            Modifier.weight(2f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                ) {
                    AsyncImage(
                        modifier = Modifier.height(150.dp),
                        model = R.drawable.user_woman,
                        contentDescription = "user_image",
                    )
                }

                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "Hello, Cindy",
                    )
                }
            }
        }
        Column(
            Modifier
                .weight(1f)
                .padding(end = 0.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
                    .clip(CircleShape)
            ) {
                Icon(
                    tint = Color.Gray,
                    painter = rememberVectorPainter(image = Icons.Filled.Notifications),
                    contentDescription = "",
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }

}

@Composable
fun CategoryMenu() {

    val categories: MutableList<RecipeCategory> = mutableListOf()
    categories.add(RecipeCategory(1, "Breakfast", R.drawable.ic_baguette))
    categories.add(RecipeCategory(2, "Lunch", R.drawable.ic_lunch))
    categories.add(RecipeCategory(1, "Dinner", R.drawable.ic_food_drumstick))
    categories.add(RecipeCategory(2, "Dessert", R.drawable.ic_ice_cream))
    categories.add(RecipeCategory(1, "Western", R.drawable.ic_pizza))
    categories.add(RecipeCategory(2, "Asian", R.drawable.ic_rice))
    categories.add(RecipeCategory(1, "Drinks", R.drawable.ic_drink))
    categories.add(RecipeCategory(2, "Meat", R.drawable.ic_meat))

    Column(Modifier.padding(start = 0.dp, end = 0.dp, top = 30.dp)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4)//GridCells.Adaptive(minSize = 128.dp),
        ) {
            items(categories.size) { item ->
                CategoryItemTemplate(categories, item)

            }

        }
    }
}

@Composable
private fun CategoryItemTemplate(
    categories: MutableList<RecipeCategory>,
    item: Int
) {
    Column(
        // modifier = Modifier.background(Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(
                    1.dp, Color.Gray, shape = CircleShape
                )
        ) {
            Image(
                painterResource(categories[item].imageResId),
                contentDescription = "",
                modifier = Modifier.size(22.dp)
            )
        }

        Text(
            text = categories[item].name,
            modifier = Modifier.padding(top = 8.dp),
            color = Color.Gray
        )

    }
}


@Composable
fun SimpleSectionTemplate(modifier: Modifier = Modifier) {
    Row(Modifier.padding(top = 16.dp)) {
        Column(Modifier.weight(1f)) { Text("Easy & Simple Recipe") }
        Column(Modifier.weight(1f), horizontalAlignment = Alignment.End) { Text("See More") }
    }
}

@Composable
fun SimpleRecipeItemList(recipes: List<Recipe> = emptyList()) {
    if (recipes.isNotEmpty()) {
        LazyRow {
            items(recipes.size, itemContent = { index ->
                val currentRecipe = recipes[index]
                //PizzaCard()
                SimpleRecipeItemInfoTemplate(recipe = currentRecipe)
            })
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SimpleRecipeItemInfoTemplate(recipe: Recipe) {
    Column(
        Modifier
            .padding(top = 16.dp, end = 12.dp)
    ) {
        Row {
            AsyncImage(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                model = recipe.image,
                contentDescription = "user_image",
                placeholder = painterResource(R.drawable.ic_loading),
            )
        }
        Row(Modifier.wrapContentWidth()) {
            Text(
                recipe.name.toString(),
                modifier = Modifier.width(120.dp),
                fontSize = 12.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFD700) // Gold color
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "4.9 | 5k Reviews",
                    color = Color(0xFFA6A6A6),
                    fontSize = 11.sp,
                    lineHeight = 10.sp,
                    textDecoration = TextDecoration.Underline
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    modifier = Modifier.height(12.dp),
                    model = R.drawable.ic_verified,
                    contentDescription = "verified badge",
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Gordon Ramsay",
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleRecipeHome(
    navController: NavController
) {
    val dashboardViewModel = koinViewModel<DashboardViewModel>()
    val recipes by dashboardViewModel.recipe.collectAsState()

    LaunchedEffect(Lifecycle.State.CREATED) {
        if (recipes.isEmpty()) {
            dashboardViewModel.getRecipes()
        }
    }

    Column(Modifier.padding(start = 16.dp, end = 16.dp, top = 40.dp)) {
        TopAppBar()
        CategoryMenu()
        SimpleSectionTemplate()
        SimpleRecipeItemList(recipes = recipes)

        /*if (false) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn {
                items(recipes.size) { pos ->
                    RecipeCard(recipes[pos], onItemClick = { recipe ->
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "recipe",
                            recipe
                        )
                        navController.navigate(NavRoute.DETAILS.name)
                    })
                }
            }
        }*/
    }

}