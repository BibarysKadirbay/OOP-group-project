package dostup;

import models.Role;
import java.util.Map;
import java.util.Set;

public class RoleManager {
    private static final Map<Role, Set<String>> rolePermissions = Map.of(
            Role.ADMIN, Set.of("ADD_GRADE", "VIEW_GRADES", "EDIT_GRADES", "DELETE_GRADES"),
            Role.MANAGER, Set.of("ADD_GRADE", "VIEW_GRADES", "EDIT_GRADES"),
            Role.EDITOR, Set.of("VIEW_GRADES", "EDIT_GRADES"),
            Role.STUDENT, Set.of("VIEW_GRADES")  // Students can only view their own grades
    );

    public static boolean hasAccess(Role role, String action) {
        return rolePermissions.getOrDefault(role, Set.of()).contains(action);
    }
}