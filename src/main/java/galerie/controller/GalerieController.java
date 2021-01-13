package galerie.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import galerie.dao.GalerieRepository;
import galerie.entity.Galerie;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Edition des catégories, sans gestion des erreurs
 */
@Controller 
@RequestMapping(path = "/galerie") 
public class GalerieController {

	@Autowired
	private GalerieRepository dao;

	/**
	 * Affiche toutes les catégories dans la base
	 * @param model pour transmettre les informations à la vue
	 * @return le nom de la vue à afficher ('showCategories.html')
	 */
	@GetMapping(path = "show")
	public	String afficheToutesLesCategories(Model model) {
		model.addAttribute("galeries", dao.findAll());
		return "afficheGaleries";
	}	
		
	/**
	 * Montre le formulaire permettant d'ajouter une catégorie
	 * @param categorie initialisé par Spring, valeurs par défaut à afficher dans le formulaire 
	 * @return le nom de la vue à afficher ('formulaireCategorie.html')
	 */
	@GetMapping(path = "add")
	public String montreLeFormulairePourAjout(@ModelAttribute("galerie") Galerie galerie) {
		return "formulaireGalerie";
	}
        
	/**
	 * Appelé par 'formulaireCategorie.html', méthode POST
	 * @param categorie Une catégorie initialisée avec les valeurs saisies dans le formulaire
	 * @return une redirection vers l'affichage de la liste des catégories
	 */
	@PostMapping(path = "save")
	public String ajouteLaCategoriePuisMontreLaListe(Galerie galerie) {
		// cf. https://www.baeldung.com/spring-data-crud-repository-save
		dao.save(galerie); // Ici on peut avoir une erreur (doublon dans un libellé par exemple)
		return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste		
	}	

	/**
	 * Appelé par le lien 'Supprimer' dans 'showCategories.html'
	 * @param categorie à partir du code de la catégorie transmis en paramètre, 
	 *                  Spring fera une requête SQL SELECT pour chercher la catégorié dans la base
	 * @return une redirection vers l'affichage de la liste des catégories
	 */
	@GetMapping(path = "delete")
	public String supprimeUneCategoriePuisMontreLaListe(@RequestParam("code") Galerie galerie) {
		dao.delete(galerie); // Ici on peut avoir une erreur (Si il y a des produits dans cette catégorie par exemple)
		return "redirect:show"; // on se redirige vers l'affichage de la liste
	}
}
