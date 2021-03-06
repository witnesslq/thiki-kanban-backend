package org.thiki.kanban.teams.teamMembers;

import org.springframework.hateoas.Link;
import org.thiki.kanban.foundation.common.RestResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by 濤 on 7/26/16.
 */
public class TeamMembersResource extends RestResource {
    public TeamMembersResource(String teamId, TeamMember teamMember) {
        this.domainObject = teamMember;
        if (teamMember != null) {
            Link selfLink = linkTo(methodOn(TeamMembersController.class).joinTeam(teamMember, teamId, null)).withSelfRel();
            this.add(selfLink);
        }
    }
}
