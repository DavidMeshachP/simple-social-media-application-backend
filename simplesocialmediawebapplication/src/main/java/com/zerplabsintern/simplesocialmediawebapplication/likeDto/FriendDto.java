package com.zerplabsintern.simplesocialmediawebapplication.likeDto;

public class FriendDto {

    private Long userId;

    private Long FriendId;

    public FriendDto() {
        
    }

    public FriendDto(Long userId, Long friendId) {
        this.userId = userId;
        FriendId = friendId;
    }



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return FriendId;
    }

    public void setFriendId(Long friendId) {
        FriendId = friendId;
    }

    
    
}
