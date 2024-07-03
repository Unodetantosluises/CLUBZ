package com.rungroup.web.mapper;

import com.rungroup.web.dto.PostDto;
import com.rungroup.web.models.Category;
import com.rungroup.web.models.Post;

import java.util.Set;
import java.util.stream.Collectors;

public class PostMapper {
    public static Post mapToPost(PostDto postDto){
        Post post = Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .textPost(postDto.getTextPost())
                .author(postDto.getAuthor())
                .photoUrl(postDto.getPhotoUrl())
                .status(postDto.getStatus())
                .createdOn(postDto.getCreatedOn())
                .updateOn(postDto.getUpdateOn())
                .categories(mapCategoryNamesToCategories(postDto.getCategories()))
                .build();
        return post;
    }
    public static PostDto mapToPostDto(Post post){
        PostDto postDto = PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .textPost(post.getTextPost())
                .author(post.getAuthor())
                .photoUrl(post.getPhotoUrl())
                .status(post.getStatus())
                .createdOn(post.getCreatedOn())
                .updateOn(post.getUpdateOn())
                .categories(mapCategoriesToCategoryNames(post.getCategories()))
                .build();
        return postDto;
    }

    private static Set<Category> mapCategoryNamesToCategories(Set<String> categoryNames){
        if(categoryNames == null) {
            return null;
        }
        return categoryNames.stream()
                .map(name -> Category.builder().name(name).build())
                .collect(Collectors.toSet());
    }

    private static Set<String> mapCategoriesToCategoryNames(Set<Category> categories){
        if(categories == null){
            return null;
        }
        return categories.stream()
                .map(Category::getName)
                .collect(Collectors.toSet());
    }
}
