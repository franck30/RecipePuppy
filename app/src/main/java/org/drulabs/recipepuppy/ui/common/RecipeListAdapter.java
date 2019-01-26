package org.drulabs.recipepuppy.ui.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.drulabs.presentation.entities.PresentationRecipe;
import org.drulabs.recipepuppy.R;
import org.drulabs.recipepuppy.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeVH> {

    private List<PresentationRecipe> recipes;

    public RecipeListAdapter() {
        recipes = new ArrayList<>();
    }

    public void populateRecipes(List<PresentationRecipe> recipes) {
        this.recipes.clear();
        this.recipes.addAll(recipes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View convertView = inflater.inflate(R.layout.item_recipe, parent, false);
        return (new RecipeVH(convertView));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeVH recipeVH, int index) {
        PresentationRecipe recipe = recipes.get(index);
        recipeVH.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class RecipeVH extends RecyclerView.ViewHolder {
        ImageView imgRecipe;
        TextView tvRecipeName;
        ChipGroup ingredientChipsGroup;

        RecipeVH(@NonNull View itemView) {
            super(itemView);
            imgRecipe = itemView.findViewById(R.id.img_recipe);
            tvRecipeName = itemView.findViewById(R.id.tvRecipeName);
            ingredientChipsGroup = itemView.findViewById(R.id.ingredients_chips_group);
        }

        void bind(PresentationRecipe recipe) {
            GlideApp.with(itemView)
                    .load(recipe.getThumbnailUrl())
                    .into(imgRecipe);
            tvRecipeName.setText(recipe.getName());
            ingredientChipsGroup.removeAllViews();
            for (String ingredient : recipe.getIngredientList()) {
                Chip ingredientChip = new Chip(itemView.getContext());
                ingredientChip.setText(ingredient);
                ingredientChipsGroup.addView(ingredientChip);
            }
        }
    }

}
