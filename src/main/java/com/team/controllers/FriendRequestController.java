package com.team.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class FriendRequestController {

//    @Autowired
//    private FriendRequestService friendRequestService;
//
//    /**
//     * @param userId
//     * @return
//     */
//    @MessageMapping(value = "/friendRequest/{userId}/all")
//    @SendTo("/topic/friendRequest")
//    public ResponseEntity<?> getAllFriendRequests(@PathVariable String userId){
//        return friendRequestService.getAllFriendRequests(userId);
//    }
//
//    /**
//     * @param senderId
//     * @param receiverId
//     * @return
//     */
//    @MessageMapping(value = "/friendRequest/{senderId}/sendTo/{receiverId}")
//    @SendTo("/topic/friendRequest")
//    public ResponseEntity<?> newFriendRequest(@DestinationVariable String senderId, @DestinationVariable String receiverId){
//        return friendRequestService.newFriendRequest(senderId,receiverId);
//    }
//
//    /**
//     * @param userId
//     * @param friendRequestId
//     * @return
//     */
//    @MessageMapping(value = "/friendRequest/{userId}/accept/{friendRequestId}")
//    @SendTo("/topic/friendRequest/action")
//    public ResponseEntity<?> acceptFriendRequest(@DestinationVariable String userId,@DestinationVariable String friendRequestId){
//        return friendRequestService.acceptFriendRequest(userId,friendRequestId);
//    }
//
//    /**
//     * @param userId
//     * @param friendRequestId
//     * @return
//     */
//    @MessageMapping(value = "/friendRequest/{userId}/decline/{friendRequestId}")
//    @SendTo("/topic/friendRequest/action")
//    public ResponseEntity<?> declineFriendRequest(@DestinationVariable String userId,@DestinationVariable String friendRequestId){
//        return friendRequestService.declineFriendRequest(userId,friendRequestId);
//    }
}
