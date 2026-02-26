package com.eduplatlearn.infrastructure.runner;

import com.eduplatlearn.entity.*;
import com.eduplatlearn.entity.Module;
import com.eduplatlearn.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Transactional
public class JpaExecutionAnalysisRunner implements CommandLineRunner {

    private final CoursRepository coursRepository;
    private final EnseignantRepository enseignantRepository;
    private final LeconRepository leconRepository;
    private final ModuleRepository moduleRepository;
    private final RessourceRepository ressourceRepository;

    public JpaExecutionAnalysisRunner(
            CoursRepository coursRepository,
            EnseignantRepository enseignantRepository,
            LeconRepository leconRepository,
            ModuleRepository moduleRepository,
            RessourceRepository ressourceRepository){
        this.coursRepository = coursRepository;
        this.enseignantRepository = enseignantRepository;
        this.leconRepository = leconRepository;
        this.moduleRepository = moduleRepository;
        this.ressourceRepository = ressourceRepository;
    }


    @Override
    public void run(String... args) throws Exception {

//======================== ENSEIGNANT ===================================
        //afficherEnseignant();
        //creerEnseignant();
        //modifierEnseignant(1L,"david");
        //supprimerEnseignant(1L);
//======================== LECON ========================================
        //afficherLecon();
        //creerLecon();
        //modifierLecon(1L,"comment coder plus simplement !");
        //supprimerLecon(1L);
//======================== MODULES ======================================
        //afficherModule();
        //creermodule();
        //modifierModule(74L, "Module Demo 2");
        //supprimerModule(74L);
//======================== RESSOURCES ===================================
        //afficherRessources();
        //supprimerRessources(1L); //n'oubliez pas que pour supprimer une ressource , il faut supprimer la lecon d'abord .
//++++++++++++++++++++++++ VIDEO ++++++++++++++++++++++++++++++++++++++++
        //creerRessourcesVideo();
        //modifierRessourcesVideo(1L,"http://tubidy.com",2500);
//++++++++++++++++++++++++ TEXTE ++++++++++++++++++++++++++++++++++++++++
        //creerRessourcesTexte();
        //modifierRessourcesTexte();
//++++++++++++++++++++++++ FICHIER ++++++++++++++++++++++++++++++++++++++
        //creerRessourcesFichier();
        //modifierRessourceFichier(1L,"");
//======================== COURS ========================================
        //creerCours();
        //afficherCours();
        //modifierCours(41L,"TOUS à l'eau ");//ici on insere la ligne ainsi que le titre à modifier
        //supprimerCours(41L);

        //joint_part_module_cours ();

    }
//======================= REQUETES DE COURS ===============================
    private void afficherCours(){
        System.out.println("\nListe des cours : ");
        List<Cours> cours = coursRepository.findAll();

        cours.forEach(c -> {
            //System.out.println("Cours : " + c.getTitre()); ok
            //System.out.println("Cours : " + c.getDescription()); ok
            //System.out.println("Cours : " + c.getCreatedAt()); ok
            //System.out.println("Cours : " + c.getNiveau()); ok
            //System.out.println("Cours : " + c.getId()); pour l'instant ca ne fonctionne pas
            //System.out.println("Cours : " + c.getUpdatedAt()); ok
            //System.out.println("Nb modules : " + c.getModules().size()); ok
            //System.out.println("nb Enseignant : " + c.getEnseignants().size());
        });
    }

    private void creerCours(){
        System.out.println("===== CREATION DU COURS ======");

        Cours newCours = new Cours();
        newCours.setTitre("Cours Démo - Persistence Context");
        newCours.setDescription("Insertion pour demarrer le CRUD");
        newCours.setNiveau("Master 1");
        newCours.setPublie(true);
        Cours saved = coursRepository.save(newCours);
        Long id = saved.getId();
        System.out.println("ID generé = " + id);

    }
    private void modifierCours(Long id, String nouveauTitre){
        //on cherche le cours ,si iln'existe  pas on s'arrete
        Cours cours = coursRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cours non trouvé"));

        cours.setTitre(nouveauTitre);
        cours.setPublie(false);// Exemple de changement de statut

        coursRepository.save(cours);

        System.out.println("Cours mis à jours : " + cours.getTitre());

    }
    private void supprimerCours(Long id){
        System.out.println("================== SUPPRESSION DU COURS ID : " + id + "=======");

        if(coursRepository.existsById(id))
        {
            coursRepository.deleteById(id);
            System.out.println("SUPPRESSION REUSSI !");

        }else
        {
            System.out.println("Erreur : le cours avec l'ID " + id + "n'existe pas !");
        }
    }

//===================== REQUETES DE ENSEIGNANTS =============================
    private void afficherEnseignant(){
        System.out.println("\nListe des enseignants : ");
        List<Enseignant> enseignants = enseignantRepository.findAll();

        //List<Enseignant> enseignants = enseignantRepository.findById();
        enseignants.forEach(e -> {
            System.out.println("Enseignant : " + e.getNom());
            System.out.println("Nb cours : " + e.getCours().size());
        });
    }
    private void creerEnseignant(){
        Enseignant enseignant = new Enseignant();
        enseignant.setNom("Yapi");
        enseignant.setPrenom("Yapo Paul Emmanuel");
        enseignant.setBio("matiere 1");
        enseignant.setEmail("Emmanuelpaulo567@gmail.com");
        //ASSOCIATION AVEC LA TABLE COURS
        Cours newCours = coursRepository.getReferenceById(37L);
        // ASSOCIATION : Ajouter le cours à la liste de l'enseignant
        // Comme c'est une List, on utilise .add()
        enseignant.getCours().add(newCours);
        //COHERENCE AVEC L'OBJET

        // COHERENCE OBJET (Optionnel mais recommandé)
        // Si tu as une liste d'enseignants dans ton entité Cours, ajoute l'enseignant dedans
        newCours.getEnseignants().add(enseignant);

        //PERSISTENCE
        enseignantRepository.save(enseignant);

    }
    //=========================Modifiez la ligne du nom d'un enseignant =================
    private void modifierEnseignant(Long id, String nouveauNom){
        //on cherche le cours ,si iln'existe  pas on s'arrete
        Enseignant enseignant = enseignantRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Enseignant non trouvé"));

        enseignant.setNom(nouveauNom);


        enseignantRepository.save(enseignant);

        System.out.println("Enseignant mis à jour : " + enseignant.getNom());

    }
    //===========================Supprimez la ligne d'un enseignant==========================
    private void supprimerEnseignant(Long id){
        System.out.println("================== SUPPRESSION D'UN ENSEIGNANT ID : " + id + "=======");

        if(enseignantRepository.existsById(id))
        {
            enseignantRepository.deleteById(id);
            System.out.println("SUPPRESSION REUSSI !");

        }else
        {
            System.out.println("Erreur : l'enseignant avec l'ID " + id + "n'existe pas !");
        }
    }
//=================== REQUETES DE LECON ======================================
    private void afficherLecon(){
        System.out.println("\nListe des lecons : ");
        List<Lecon> lecons = leconRepository.findAll();

        lecons.forEach(l -> {
            System.out.println("Lecon : " + l.getTitre());
            //System.out.println("Nb de Lecon : " + l.getModule().getModules().size());
            //System.out.println("Lecon : " + l.getResume());
            //System.out.println("Lecon : " + l.getTitre());
            //System.out.println("Lecon : " + l.getOrdre());
            //System.out.println("Lecon : " + l.getDureeMinutes());
            //System.out.println("Ressources : " + l.getRessources());

        });
    }
    private void creerLecon(){
        Lecon lecon = new Lecon();
        lecon.setTitre("Comment coder : demo");
        lecon.setResume("Ici on vous montre comment on code et surtout comment on s'y prends");
        lecon.setOrdre(10);
        lecon.setDureeMinutes(3600);
        //ASSOCIATION AVEC LA TABLE MODULE
        Module newModule = moduleRepository.getReferenceById(68L);
        // ASSOCIATION : Ajouter le module à la liste de lecon 1-N
        lecon.setModule(newModule);
        //COHERENCE AVEC L'OBJET
        newModule.getModules().add(lecon);

        //PERSISTENCE
        leconRepository.save(lecon);

    }
    //=========================Modifiez la ligne du titre d'une lecon =================
    private void modifierLecon(Long id, String nouveauTitre){
        //on cherche la lecon ,si elle n'existe  pas on s'arrete
        Lecon lecon = leconRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Lecon non trouvé"));

        lecon.setTitre(nouveauTitre);


        leconRepository.save(lecon);

        System.out.println("Lecon mis à jour : " + lecon.getTitre());

    }
    //===========================Supprimez la ligne d'un module==========================
    private void supprimerLecon(Long id){
        System.out.println("================== SUPPRESSION DE LA LECON ID : " + id + "=======");

        if(leconRepository.existsById(id))
        {
            leconRepository.deleteById(id);
            System.out.println("SUPPRESSION REUSSI !");

        }else
        {
            System.out.println("Erreur : la lecon avec l'ID " + id + "n'existe pas !");
        }
    }
//================== REQUETES DE MODULES ======================================
    private void afficherModule(){
        System.out.println("\nListe des modules : ");
        List<Module> modules = moduleRepository.findAll();

        modules.forEach(m -> {
            //System.out.println("Modules : " + m.getTitre());ok
            //System.out.println("Modules : " + m.getDescription());ok
            /*afficher m.getCours() reviens à montrer le hashcode de l'objet en java*/
            //System.out.println("Modules : " + m.getCours().getTitre());//pour afficher les cours sans avoir le resultat hashcode
            //System.out.println("Modules : " + m.getCreatedAt());ok
            //System.out.println("Modules : " + m.getOrdre());ok
            //System.out.println("Modules : " + m.getUpdatedAt());
        });
    }
    private void creermodule(){
        Module module = new Module();
        module.setTitre("Module Demo 1 ");
        module.setDescription("Module creer pour demonstration du lazy loading partie 2");
        module.setOrdre(17);
        //ASSOCIATION AVEC LE COURS
        Cours newCours = coursRepository.getReferenceById(40L);
        module.setCours(newCours);
        //COHERENCE COTE OBJET
        newCours.getModules().add(module);
        //PERSISTENCE
        moduleRepository.save(module);
        System.out.println("CREATION DE MODULE OK !");
        //System.out.println("======= LAZY LOADING =========");
        //Cours coursL = coursRepository.findById(newCours.getId()).orElseThrow();
        //System.out.println("Cours: " + coursL.getTitre());
        //DECLENCHEMENT
        //System.out.println("NB modules : " + coursL.getModules().size());
    }
    //=========================Modifiez la ligne du titre d'un module =================
    private void modifierModule(Long id, String nouveauTitre){
        //on cherche le cours ,si iln'existe  pas on s'arrete
        Module module = moduleRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Module non trouvé"));

        module.setTitre(nouveauTitre);


        moduleRepository.save(module);

        System.out.println("Module mis à jour : " + module.getTitre());

    }
    //===========================Supprimez la ligne d'un module==========================
    private void supprimerModule(Long id){
        System.out.println("================== SUPPRESSION DU MODULE ID : " + id + "=======");

        if(moduleRepository.existsById(id))
        {
            moduleRepository.deleteById(id);
            System.out.println("SUPPRESSION REUSSI !");

        }else
        {
            System.out.println("Erreur : le module avec l'ID " + id + "n'existe pas !");
        }
    }
//================= REQUETES DE RESSOURCES ====================================
    /*
    * Unique Constraint : Comme tu as mis unique = true,
    * si la leçon ID 1 possède déjà une ressource,
    * Hibernate enverra une erreur de contrainte SQL.
    * Tu ne peux pas lier deux ressources à la même leçon.
    *
    * */
    private void afficherRessources(){
        System.out.println("\nListe des ressources : ");
        List<Ressources> ressources = ressourceRepository.findAll();

        ressources.forEach(r -> {
            System.out.println("Ressources : " + r.getTitre());
            //System.out.println("Ressources : " + r.getCreatedAt());
            //System.out.println("Ressources : " + r.getUpdatedAt());
        } );
    }
    private void supprimerRessources(long id){
        System.out.println("================== SUPPRESSION DE LA RESSOURCE ID : " + id + "=======");

        if(ressourceRepository.existsById(id))
        {
            ressourceRepository.deleteById(id);
            System.out.println("SUPPRESSION REUSSI !");

        }else
        {
            System.out.println("Erreur : la ressource avec l'ID " + id + "n'existe pas !");
        }
    }
//+++++++++++++++++++++++ RESSOURCES VIDEO ++++++++++++++++++++++++++++++++++++++
    private void creerRessourcesVideo(){
        System.out.println("======================= CREATION VIDEO ===================");
        //INSTANTIATION DE VIDEO (ENFANT)
        Video video = new Video();

        //REMPLISSAGE DU CHAMP PARENT(RESSOURCES)
        video.setTitre("Tuto Hibernate joined !");

        //REMPLISSAGE DU CHAMP SPECIFIQUE A L'ENFANT(VIDEO)
        video.setUrl("http://linked.com");
        video.setDureeSecondes(300);

        //ASSOCIATION AVEC LECON
        Lecon newLecon = leconRepository.getReferenceById(1L);
        video.setLecon(newLecon);
        //COHERENCE COTE OBJET
        newLecon.setRessources(video);

        //ENREGISTREMENT DES INFORMATIONS A PARTIR DU REPOSITORY PARENT OU ENFANT (SI ON L'A)
        ressourceRepository.save(video);

        System.out.println("video enregistrée");

    }
    //======= Modification d'une ligne specifique à video
    private void modifierRessourcesVideo(Long id, String nouvelleUrl, int nouvelleDureeSeconde){
        System.out.println("======================= MODIFICATION VIDEO ===================");
        //on cherche la ressource parente pour commencer
        Ressources ressources = ressourceRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Ressources non trouvé !"));

        //On verifie si c'est bien une video avant de modifier L'URL
        if (ressources instanceof Video video){
            video.setUrl(nouvelleUrl);
            video.setDureeSecondes(nouvelleDureeSeconde);
            //MISE A JOUR DE LA VIDEO
            ressourceRepository.save(video);
            System.out.println("MISE A JOUR DES CHAMPS DES VIDEO !");
        }else{
            System.out.println("Erreur :  Cette ressource n'est pas une video. ");
        }

    }

//+++++++++++++++++++++++++ RESSOURCES TEXTE ++++++++++++++++++++++++++++++++++++++
    private void creerRessourcesTexte(){
        System.out.println("======================= CREATION TEXTE ===================");
        //INSTANTIATION DE TEXTE (ENFANT)
        texte texte = new texte();

        //REMPLISSAGE DU CHAMP PARENT(RESSOURCES)
        texte.setTitre("Tuto Hibernate joined  version texte!");

        //REMPLISSAGE DU CHAMP SPECIFIQUE A L'ENFANT(TEXTE)
        texte.setContenu("tout ce qu'il faut savoir sur hibernate version texte");


        //ASSOCIATION AVEC LECON
        Lecon newLecon = leconRepository.getReferenceById(1L);
        texte.setLecon(newLecon);
        //COHERENCE COTE OBJET
        newLecon.setRessources(texte);

        //ENREGISTREMENT DES INFORMATIONS A PARTIR DU REPOSITORY PARENT OU ENFANT (SI ON L'A)
        ressourceRepository.save(texte);

        System.out.println("TEXTE enregistrée");
    }
    //======= Modification d'une ligne specifique au texte
    private void modifierRessourcesTexte(Long id, String nouveauContenu){
        System.out.println("======================= MODIFICATION TEXTE ===================");
        //on cherche la ressource parente pour commencer
        Ressources ressources = ressourceRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Ressources non trouvé !"));

        //On verifie si c'est bien un texte avant de modifier Les champs
        if (ressources instanceof texte texte){
            texte.setContenu(nouveauContenu);

            //MISE A JOUR DU TEXTE
            ressourceRepository.save(texte);
            System.out.println("MISE A JOUR DES CHAMPS DU TEXTE");
        }else{
            System.out.println("Erreur :  Cette ressource n'est pas un texte. ");
        }

    }
//++++++++++++++++++++++++++++ RESSOURCES FICHIER +++++++++++++++++++++++++++++++++++++
    private void creerRessourcesFichier(){
        System.out.println("======================= CREATION FICHIER ===================");
        //INSTANTIATION DE FICHIER (ENFANT)
        Fichier fichier = new Fichier();

        //REMPLISSAGE DU CHAMP PARENT(RESSOURCES)
        fichier.setTitre("Tuto Hibernate joined  version texte!");

        //REMPLISSAGE DU CHAMP SPECIFIQUE A L'ENFANT(FICHIER)
        fichier.setNomfichier("hibernate_cours");
        fichier.setCheminStockage("");


        //ASSOCIATION AVEC LECON
        Lecon newLecon = leconRepository.getReferenceById(1L);
        fichier.setLecon(newLecon);
        //COHERENCE COTE OBJET
        newLecon.setRessources(fichier);

        //ENREGISTREMENT DES INFORMATIONS A PARTIR DU REPOSITORY PARENT OU ENFANT (SI ON L'A)
        ressourceRepository.save(fichier);

        System.out.println("FICHIER enregistrée");
    }
    //======= Modification d'une ligne specifique au fichier
    private void modifierRessourceFichier(Long id, String nouveauNomfichier){
        System.out.println("======================= MODIFICATION FICHIER ===================");
        //on cherche la ressource parente pour commencer
        Ressources ressources = ressourceRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Ressources non trouvé !"));

        //On verifie si c'est bien une fichier avant de modifier Les champs
        if (ressources instanceof Fichier fichier){
            fichier.setNomfichier(nouveauNomfichier);

            //MISE A JOUR DU FICHIER
            ressourceRepository.save(fichier);
            System.out.println("MISE A JOUR DES CHAMPS DU FICHIER !");
        }else{
            System.out.println("Erreur :  Cette ressource n'est pas une fichier. ");
        }

    }
    //========================= JOINT PARTIE 4 MODULE-COURS =======================
    private void joint_part_module_cours(){

        //nettoyage
        moduleRepository.deleteAll();
        coursRepository.deleteAll();

        for(int i = 1 ; i <= 3 ; i++)
        {
            Cours newCours = new Cours();
            newCours.setTitre("Cours " + i);
            newCours.setDescription("Cours démo N+1 - " + i);
            newCours.setNiveau("Master 1");
            newCours.setPublie(true);
            Cours saved = coursRepository.save(newCours);

            for (int j = 1 ; j <= 2 ; j++)
            {
                Module module = new Module();
                module.setTitre("Module Demo " + i);
                module.setDescription("Module demo N+1 - " + i);
                module.setOrdre(1);
                //ASSOCIATION AVEC LE COURS
                module.setCours(newCours);
                //COHERENCE COTE OBJET
                newCours.getModules().add(module);
                //PERSISTENCE
                moduleRepository.save(module);
            }
        }

        System.out.println("======= SLIDE 5 - N+1 =======");
        List<Cours> coursList = coursRepository.findAllWithModules();
        for(Cours c : coursList){
            System.out.println("Cours : " + c.getTitre());
            System.out.println("Nb modules N+1 : " + c.getModules().size());
        }
    }


}
