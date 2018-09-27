package lt.bt.security.security.web;

import lt.bt.security.security.entity.Authority;
import lt.bt.security.security.entity.Role;
import lt.bt.security.security.repository.AuthorityRepository;
import lt.bt.security.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @RequestMapping("/roles")
    public String fetchRoles(Model model) {

        List<Role> roles = (List<Role>) roleRepository.findAll();

        model.addAttribute("roles", roles);

        return "admin/roles";

    }

    @RequestMapping("/role/{id}")
    public String  editRole(@PathVariable  Long id, Model model) {
        Role role = roleRepository.findById(id).get();

        model.addAttribute("role", role);

        return "admin/role";
    }

    @RequestMapping("/role/new")
    public String newRole(Role role) {
        return "admin/create-role";
    }

    @RequestMapping(value = "/role/new", method = RequestMethod.POST)
    public String createRole(Role role) {
        roleRepository.save(role);

        return "redirect:/admin/roles";

    }

    @RequestMapping("/authority/{id}/new")
    public String newAuthority(@PathVariable Long id, Model model) {

        model.addAttribute("role_id", id);

        return "admin/authority";
    }

    @RequestMapping(value = "/authority/{id}/new", method = RequestMethod.POST)
    public String createAuthority(@PathVariable Long id, @ModelAttribute("authority") String name, Model model) {

        Authority authority = new Authority();
        authority.setName(name);

        Role role = roleRepository.findById(id).get();

        authority = authorityRepository.save(authority);

        role.addAuthority(authority);

        roleRepository.save(role);


        return "redirect:/admin/role/" + id;
    }

    @RequestMapping("/authority/{roleId}/delete/{authorityId}")
    public String deleteAuthority(@PathVariable Long roleId, @PathVariable Long authorityId,  Model model) {

        authorityRepository.deleteById(authorityId);

        return "redirect:/admin/role/" + roleId;
    }

    @RequestMapping("/role/{id}/delete")
    public String deleteRole(@PathVariable Long id, Model model) {

        roleRepository.deleteById(id);

        return "redirect:/admin/roles/";
    }
}
