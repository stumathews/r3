/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL.Interfaces;

/**
 *
 * @author Stuart
 */
public interface ICharacteristicReviewDAO 
{
    DEL.CharacteristicReview getCharacteristicReview(Long id) throws Exception;
}
