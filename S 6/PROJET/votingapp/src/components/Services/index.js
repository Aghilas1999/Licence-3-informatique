import React from 'react';
import Icon1 from '../../images/entreprise.png';
import Icon2 from '../../images/uni.png';
import Icon3 from '../../images/ppIcon.png';
import {
  ServicesContainer,
  ServicesH1,
  ServicesWrapper,
  ServicesCard,
  ServicesIcon,
  ServicesH2,
  ServicesP
} from './ServicesElements';

const Services = () => {
  return (
    <ServicesContainer id='services'>
      <ServicesH1>SECTEURS</ServicesH1>
      <ServicesWrapper>
        <ServicesCard>
          <ServicesIcon src={Icon1} />
          <ServicesH2>Entreprise</ServicesH2>
          <ServicesP>
          i-voting propose des solutions aux entreprises, firmes et groupes industriels, notamment pour des votes pondérés d’actionnaires ou de partenariat.
          </ServicesP>
        </ServicesCard>
        <ServicesCard>
          <ServicesIcon src={Icon2} />
          <ServicesH2>Enseignement</ServicesH2>
          <ServicesP>
          i-voting fournit une solution de vote en ligne sécurisée et pratique pour l’enseignement universitaire ou primaire et pour les conseils scolaires.
          </ServicesP>
        </ServicesCard>
        <ServicesCard>
          <ServicesIcon src={Icon3} />
          <ServicesH2>partis politiques</ServicesH2>
          <ServicesP>
          i-voting propose à la fois des solutions de vote à distance et en direct aux partis politiques pour les congrès d’investiture et autres évènements politiques. 
          </ServicesP>
        </ServicesCard>
      </ServicesWrapper>
    </ServicesContainer>
  );
};

export default Services;
