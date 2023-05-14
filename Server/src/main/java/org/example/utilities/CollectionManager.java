package org.example.utilities;

import org.example.data.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

public class CollectionManager {
    private LinkedHashSet<Organization> organizationCollection;
    private final LocalDate creationDate;
    private LocalDate lastInitDate;
    private LocalDate lastSaveDate;
    private FileManager fileManager;
    public CollectionManager(FileManager fileManager){
        organizationCollection = new LinkedHashSet<>();
        creationDate = LocalDate.now();
        lastInitDate = null;
        lastSaveDate = null;
        this.fileManager = fileManager;
        loadCollection();
    }
    public void setFileManager(FileManager fileManager){
        this.fileManager = fileManager;
    }
    /**
     * Get the creation date of the object.
     *
     * @return The creation date of the collection.
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }
    /**
     * @return Last initialization time or null if there wasn't initialization.
     */
    public LocalDate getLastInitDate() {
        return lastInitDate;
    }

    /**
     * @return Last save time or null if there wasn't saving.
     */
    public LocalDate getLastSaveDate() {
        return lastSaveDate;
    }
    /**
     * This function returns the collection of organizations.
     *
     * @return The LinkedHashSet of Organization objects.
     */
    public LinkedHashSet<Organization> getCollection() {
        return organizationCollection;
    }
    /**
     * The setCollection function sets the organizationCollection field to the value of the
     * organizationCollection parameter.
     *
     * @param organizationCollection The collection of organizations that the user is a member of.
     */
    public void setCollection(LinkedHashSet<Organization> organizationCollection) {
        this.organizationCollection = organizationCollection;
    }
    /**
     * @return Collection content or corresponding string if collection is empty.
     */
    public String showCollection() {
        if (organizationCollection.isEmpty()) return "Collection is empty!";
        return organizationCollection.stream()
                .map(organization -> organization.toString() + "\n").collect(Collectors.joining());
    }
    /**
     * @return Name of the collection's type.
     */
    public String collectionType() {
        return organizationCollection.getClass().getName();
    }

    /**
     * @return Size of the collection.
     */
    public int collectionSize() {
        return organizationCollection.size();
    }
    /**
     * Given an id, return the organization with that id
     *
     * @param id The id of the organization to be retrieved.
     * @return the response of right execution.
     */
    public Organization getById(int id){
        return organizationCollection.stream().filter(organization -> organization.getId()==id).findFirst().orElse(null);
    }
    /**
     * Add an organization to the collection of organizations
     *
     * @param organization The organization to add to the collection.
     */
    public void addToCollection(Organization organization){
        organizationCollection.add(organization);
    }
    /**
     * Remove an organization from the collection
     *
     * @param organization The organization to be removed from the collection.
     */
    public void removeFromCollection(Organization organization){
        organizationCollection.remove(organization);
    }
    /**
     * Remove an organization from the collection if it exists
     *
     * @param id The id of the organization to remove.
     */
    public void removeFromCollectionById(Integer id){
        organizationCollection.removeIf(organization -> Objects.equals(organization.getId(), id));
    }
    /**
     * Clear the collection of all the organizations
     */
    public void clearCollection(){
        organizationCollection.clear();
    }
    /**
     * Given a collection of Organization objects, return the maximum id value of the collection.
     * If the collection is empty, return 0
     *
     * @return The id of the organization that was just added.
     */
    public int generateNewIdForOrganization(){
        if(organizationCollection.isEmpty()) return 1;
        else return organizationCollection.stream()
                .mapToInt(Organization::getId)
                .filter(organization -> organization >= 0)
                .max().orElse(0) + 1;
    }
    public void removeByIdFromCollection(int id){
        organizationCollection.stream()
                .filter(organization -> organization.getId() == id)
                .findFirst()
                .ifPresent(this::removeFromCollection);
    }

    /**
     * This function returns a string that contains information about the collection
     *
     * @return The string "Type - " + organizationCollection.getClass() + "\n" +
     *                 "Creation date - " + getCreationDate() + "\n" +
     *                 "Amount of elements - " + organizationCollection.size();
     */
    public String infoAboutCollection(){
        return "Type - " + organizationCollection.getClass() + "\n" +
                "Creation date - " + getCreationDate() + "\n" +
                "Last Init date - " + getLastInitDate() + "\n" +
                "Last Save date - " + getLastSaveDate() + "\n" +
                "Amount of elements - " + organizationCollection.size();
    }
    /**
     * Get all the organization types in the system
     *
     * @return A ArrayList of all the types of organizations in the system.
     */
    public ArrayList<OrganizationType> getAllOrganizationTypes(){
        Iterator<Organization> iterator = organizationCollection.iterator();
        ArrayList<OrganizationType> organizationTypes = new ArrayList<>();
        while (iterator.hasNext()){
            Organization organization = iterator.next();
            if(!organizationTypes.contains(organization.getType()))
                organizationTypes.add(organization.getType());
        }
        return organizationTypes;
    }
    /**
     * Saves the collection to file.
     */
    public void saveCollection() {
        fileManager.writeCollectionsOPENCSV(organizationCollection);
        lastSaveDate = LocalDate.now();
    }

    /**
     * Loads the collection from file.
     */
    private void loadCollection() {
        organizationCollection = fileManager.readCollectionsOPENCSV();
        lastInitDate = LocalDate.now();
    }
}
