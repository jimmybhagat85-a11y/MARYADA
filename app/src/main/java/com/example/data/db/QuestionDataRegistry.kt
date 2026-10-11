package com.example.data.db

import com.example.data.model.QuestionEntity
import com.example.data.model.ScoringRubricEntity

object QuestionDataRegistry {

    fun getAllScenarios(): List<QuestionEntity> {
        val list = mutableListOf<QuestionEntity>()

        // 1. CAREER & WORKPLACE (9 scenarios)
        list.add(
            QuestionEntity(
                id = "q_career_1",
                categoryId = "cat_career",
                title = "Colleague Takes Full Credit for Your Work",
                scenario = "Your colleague takes complete credit in front of the executive team for a major presentation and financial model you spent weeks perfecting. Your manager openly praises the colleague. What would you do?",
                context = "You have original document edit histories, emails, and commits proving your authorship. However, directly accusing your colleague in public could create severe department toxicity.",
                difficulty = "Foundational",
                suggestedDirections = "Confront privately first with evidence|Send polite factual follow-up email copying manager with project history|Publicly expose the colleague during next meeting|Do nothing and let karma decide",
                followUpQuestions = "Would you prioritize setting the record straight factually, preserving long-term office harmony, or giving your colleague a chance to correct their statement?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_career_2",
                categoryId = "cat_career",
                title = "Whistleblowing on Safety Shortcuts",
                scenario = "You discover your company is quietly bypassing safety testing protocols on a consumer appliance to meet a critical launch deadline. Your direct director orders you to stay silent.",
                context = "Reporting externally could risk your job, your work visa, and your family's financial stability, but staying quiet could endanger end users.",
                difficulty = "Nuanced",
                suggestedDirections = "Document risks formally in writing internally first|Immediately report to regulatory authorities anonymously|Comply with boss since the company holds legal liability|Resign quietly without taking action",
                followUpQuestions = "How do you balance your loyalty to the company and personal livelihood against the safety of innocent strangers?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_career_3",
                categoryId = "cat_career",
                title = "Managing a Struggling Subordinate Facing Crisis",
                scenario = "A member of your team has been consistently missing critical deliverables, dragging down the squad's performance. You find out their parent was recently diagnosed with a terminal illness.",
                context = "Your leadership is pressuring you to replace them immediately or risk losing client trust.",
                difficulty = "Nuanced",
                suggestedDirections = "Offer temporary leave and redistribute tasks transparently|Fire them professionally to maintain squad metrics|Cover up their missed deadlines secretly yourself|Demand they compartmentalize personal issues",
                followUpQuestions = "How can a leader balance compassionate empathy with organizational responsibility without deceiving stakeholders?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_career_4",
                categoryId = "cat_career",
                title = "Poaching Key Clients When Starting Your Own Firm",
                scenario = "You are leaving your firm to launch your own venture. Several major clients contact you asking to transfer their accounts to your new company, which would violate a non-compete clause.",
                context = "The clients were relationships you personally nurtured, but the old firm provided the platform and credentials.",
                difficulty = "Nuanced",
                suggestedDirections = "Refuse and honor non-compete clause until legal term expires|Encourage clients to terminate contract and sign with you quietly|Negotiate a formal legal transition buyout with old employer|Claim the clients sought you out freely",
                followUpQuestions = "Does professional ethics require honoring a contract even when clients genuinely prefer your service?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_career_5",
                categoryId = "cat_career",
                title = "Boss Demands Falsifying an ESG Report",
                scenario = "Your CEO asks you to massage carbon emission figures by 30% to qualify for a lucrative government green grant that will prevent employee layoffs.",
                context = "Falsification preserves dozens of jobs, while honest reporting could trigger department layoffs.",
                difficulty = "Profound",
                suggestedDirections = "Refuse to alter factual figures while proposing real reduction initiatives|Comply because preventing employee layoffs is the higher compassionate good|Leak the request anonymously to press|Resign immediately without comment",
                followUpQuestions = "Is bending the truth ever permissible if the explicit intent is shielding innocent workers from hardship?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_career_6",
                categoryId = "cat_career",
                title = "Unpaid Overtime and Exploitative Culture",
                scenario = "Your leadership expects daily 14-hour work shifts without overtime pay, promoting it as 'passion and family spirit.' Junior employees are suffering burnout and distress.",
                context = "You are in middle management and have the voice to challenge this, but questioning it risks being labeled uncommitted.",
                difficulty = "Nuanced",
                suggestedDirections = "Advocate structured boundaries and protect juniors formally|Keep quiet and focus solely on your own workload|Advise juniors to quit quietly without rocking the boat|Join leadership in demanding long hours",
                followUpQuestions = "What is the duty of a person with relative privilege when witnessing structural exploitation of juniors?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_career_7",
                categoryId = "cat_career",
                title = "Sabotaging a Toxic Rival for Promotion",
                scenario = "You learn that your chief rival for a critical promotion has made a minor accounting error. If you leak it to HR, you are guaranteed the promotion.",
                context = "Your rival has previously spread malicious gossip about you and used cutthroat tactics.",
                difficulty = "Foundational",
                suggestedDirections = "Compete solely on your own merit and refuse to leak their mistake|Privately inform the rival so they can fix it before HR discovers it|Leak it immediately to settle old scores|Let manager notice naturally",
                followUpQuestions = "Does past mistreatment by an opponent justify using opportunistic takedowns against them?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_career_8",
                categoryId = "cat_career",
                title = "Handling Confidential Knowledge of Impending Layoffs",
                scenario = "You accidentally see a confidential spreadsheet showing that your close colleague and their team will be laid off in two months. They just mentioned taking a large home mortgage.",
                context = "You signed a strict non-disclosure agreement. Warning them could breach legal confidentiality and jeopardize your position.",
                difficulty = "Profound",
                suggestedDirections = "Give subtle, prudent counsel to delay major purchases without leaking specifics|Break NDA and tell them explicitly so they can protect their family|Honor NDA completely and say nothing at all|Approach HR to plead for their exemption",
                followUpQuestions = "Where does one's duty to a legal professional vow end, and one's duty to protect a friend from catastrophe begin?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_career_9",
                categoryId = "cat_career",
                title = "A Junior Pointing Out Your Flawed Proposal in Public",
                scenario = "During an all-hands client pitch, an intern notices a critical math flaw in your projection and points it out openly in front of the client.",
                context = "Your initial reaction is embarrassment and instinctive defensive irritation at being challenged publicly.",
                difficulty = "Foundational",
                suggestedDirections = "Publicly thank the intern warmly, validate the correction, and adjust numbers|Diminish their comment as irrelevant in front of client and reprimand later|Ignore the comment and rush forward|Admit the oversight while explaining the underlying thesis",
                followUpQuestions = "How does an authentic leader demonstrate self-control and commitment to truth when their ego is bruised?"
            )
        )

        // 2. RELATIONSHIPS & BETRAYAL (9 scenarios)
        list.add(
            QuestionEntity(
                id = "q_rel_1",
                categoryId = "cat_relationships",
                title = "Confession of Past Infidelity",
                scenario = "Your long-term partner confesses that two years ago, during a period of acute strain, they had a brief lapse in fidelity which they deeply regret and never repeated.",
                context = "They came forward entirely voluntarily out of conscience, not because they were caught.",
                difficulty = "Profound",
                suggestedDirections = "Acknowledge the pain, seek deep transparent counseling, evaluate sincerity|Immediately sever the relationship permanently without dialogue|Use the confession as leverage in future disagreements|Demand punitive revenge before forgiving",
                followUpQuestions = "Can authentic remorse and voluntary truthfulness create a path for redemption, or is broken trust irreparable?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_rel_2",
                categoryId = "cat_relationships",
                title = "Partner Hiding Massive Secret Debt",
                scenario = "Shortly before your planned marriage, you discover your partner has accumulated significant gambling debt and concealed it from you.",
                context = "They beg for forgiveness, claiming they were ashamed and intended to pay it off quietly.",
                difficulty = "Nuanced",
                suggestedDirections = "Pause the wedding, require financial transparency, debt counseling, and genuine accountability|Call off the wedding immediately and expose them to both families|Quietly pay off their debt yourself to protect family pride|Demand they sign an ironclad prenuptial agreement",
                followUpQuestions = "How do you distinguish between forgiving human weakness and protecting yourself from chronic deceit?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_rel_3",
                categoryId = "cat_relationships",
                title = "A Friend Spreads a Hurtful Rumor About You",
                scenario = "A close confidant with whom you shared deeply vulnerable personal insecurities repeats them at a social gathering to win social approval.",
                context = "You are deeply wounded and your mutual circle is discussing the matter.",
                difficulty = "Foundational",
                suggestedDirections = "Calmly address the betrayal privately, set clear firm boundaries, withdraw trust|Retaliate by broadcasting their deepest secrets online|Pretend nothing happened to maintain appearances|Cut them off completely with silent hostility",
                followUpQuestions = "How can one practice self-restraint (samyama) without becoming a passive doormat to disrespect?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_rel_4",
                categoryId = "cat_relationships",
                title = "Severing Ties with a Chronic Narcissistic Sibling",
                scenario = "An elder sibling constantly manipulates family events, verbally abuses your spouse, and demands money while playing the victim.",
                context = "Traditional cultural expectations demand unconditional deference to elders.",
                difficulty = "Nuanced",
                suggestedDirections = "Establish firm, respectful non-negotiable boundaries while treating them with polite civility|Endure the mistreatment silently in the name of family honor|Counter-attack aggressively to humiliate them in front of relatives|Completely disown them without explanation",
                followUpQuestions = "Does dharma require enduring continuous toxic mistreatment, or does it demand setting principled boundaries?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_rel_5",
                categoryId = "cat_relationships",
                title = "Discovering Your Best Friend's Spouse is Cheating",
                scenario = "You accidentally see clear, undeniable proof that your best friend's spouse is having an extramarital affair with an acquaintance.",
                context = "Telling your friend will shatter their family, but staying silent feels like complicity in deceit.",
                difficulty = "Profound",
                suggestedDirections = "First confront the spouse giving them 48 hours to confess, then inform friend gently with compassion|Tell your best friend immediately with facts and unwavering emotional support|Stay out of other people's marriages entirely|Send an anonymous tip to your friend",
                followUpQuestions = "What is the duty of true friendship (Mitrata) when bearing distressing truth that will cause grief?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_rel_6",
                categoryId = "cat_relationships",
                title = "Forgiving Someone Who Shows Zero Remorse",
                scenario = "A business partner who embezzled joint funds ten years ago approaches you at a social gathering acting as if nothing happened, offering no apology or restitution.",
                context = "Relatives encourage you to 'let it go and hug them' for the sake of the occasion.",
                difficulty = "Nuanced",
                suggestedDirections = "Maintain inner peace without hatred, but decline false reconciliation without accountability|Create a public scene to demand financial restitution|Pretend warmth to appease relatives|Accept them back into partnership",
                followUpQuestions = "Is true forgiveness the same as trusting an unrepentant person again?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_rel_7",
                categoryId = "cat_relationships",
                title = "Partner's Career Opportunity Requires Relocating",
                scenario = "Your partner receives a life-changing dream opportunity abroad. Relocating would mean leaving your stable local career and family roots.",
                context = "You both love each other deeply, but neither wants to harbor unspoken resentment in ten years.",
                difficulty = "Nuanced",
                suggestedDirections = "Conduct honest mutual values exploration, evaluate compromises or trial period|Insist partner turn it down because your career was established first|Sacrifice your entire career without discussing your personal aspirations|Propose ending the relationship immediately",
                followUpQuestions = "How can a couple practice mutual dharma where neither partner is reduced to a sacrificed martyr?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_rel_8",
                categoryId = "cat_relationships",
                title = "A Former Bully Reaches Out for Help",
                scenario = "Someone who severely bullied you during your youth reaches out decades later because their child needs specialized medical guidance in your field.",
                context = "You possess the exact expertise that could alter the child's outcome, but traumatic memories resurface.",
                difficulty = "Foundational",
                suggestedDirections = "Provide full professional assistance wholeheartedly for the innocent child|Refuse outright to make them feel the pain they caused you|Help only on condition of a humiliating public apology|Refer them elsewhere without personal involvement",
                followUpQuestions = "How does Lord Ram's treatment of fallen adversaries and their dependents guide our response to past tormentors?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_rel_9",
                categoryId = "cat_relationships",
                title = "Staying in an Empty Marriage for Social Reputation",
                scenario = "You and your spouse have had zero affection or communication for years, living like estranged roommates solely to avoid societal gossip.",
                context = "Your children are now adults, yet family elders warn you that divorce brings social shame.",
                difficulty = "Nuanced",
                suggestedDirections = "Address reality honestly through mediation to pursue amicable dignified resolution|Continue the silent façade for another twenty years to protect image|Start a clandestine affair while staying married|Blame elders publicly for your unhappiness",
                followUpQuestions = "Does Maryada mean upholding deceptive external appearances, or living with truthfulness and dignity?"
            )
        )

        // 3. FAMILY & RESPONSIBILITIES (9 scenarios)
        list.add(
            QuestionEntity(
                id = "q_fam_1",
                categoryId = "cat_family",
                title = "Elderly Parents Refuse Medical Assistance",
                scenario = "Your aging parents have deteriorating health and can no longer live safely alone, but adamantly refuse assisted living or a home caretaker.",
                context = "You work in another city and cannot commute daily without resigning your livelihood.",
                difficulty = "Nuanced",
                suggestedDirections = "Spend patient time in person, build trust, involve respected family doctors and mentors|Force them into a care home against their will immediately|Abandon efforts and say 'it is their choice if they fall'|Resign job abruptly without any financial plan",
                followUpQuestions = "How do you honor parents' autonomy and dignity while fulfilling the duty of protecting their physical safety?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_fam_2",
                categoryId = "cat_family",
                title = "Unequal Ancestral Will and Sibling Resentment",
                scenario = "Your parents write a will leaving 80% of family property to you because you cared for them, while your struggling sibling receives only 20%.",
                context = "Legally the property is entirely yours, but your sibling feels deeply alienated and heartbroken.",
                difficulty = "Profound",
                suggestedDirections = "Voluntarily share the inheritance equitably with your sibling to heal family ties|Keep all 80% strictly, asserting parents' absolute legal rights|Cut all contact with sibling if they complain|Give 100% away to avoid conflict entirely",
                followUpQuestions = "How does Bharata's refusal of unilateral privilege at Chitrakuta illuminate family inheritance disputes?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_fam_3",
                categoryId = "cat_family",
                title = "Protecting a Reckless Sibling in Legal Trouble",
                scenario = "Your younger sibling was involved in a drunk driving accident that damaged parked vehicles and begs you to say you were behind the wheel.",
                context = "They face potential jail time and loss of job, while your clean record might result in a minor fine.",
                difficulty = "Foundational",
                suggestedDirections = "Refuse to take false blame, but stand by their side to face accountability with legal counsel|Lie to police and take the blame to protect your sibling|Disown the sibling and refuse to visit them in custody|Pay off the witnesses to bury the evidence",
                followUpQuestions = "Is enabling deceit true family love, or is guiding a loved one through lawful accountability the real dharmic duty?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_fam_4",
                categoryId = "cat_family",
                title = "Parent Disapproves of Life Partner Due to Caste or Religion",
                scenario = "Your parents threaten to sever all ties and declare social boycott if you proceed to marry the person you love because of traditional caste/social differences.",
                context = "Your partner is deeply ethical, loving, and supportive in every way.",
                difficulty = "Nuanced",
                suggestedDirections = "Respectfully and calmly stand by your principled choice while maintaining love and open doors for parents|Capitulate immediately and abandon partner to avoid parental anger|Ellope secretly and send a hostile message to parents|Demand your partner change their identity to appease relatives",
                followUpQuestions = "How does Rama's celebration of Shabari and Guha demonstrate that true character transcends birth caste and orthodoxy?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_fam_5",
                categoryId = "cat_family",
                title = "In-laws Demanding Traditional Dowry Disguised as 'Gifts'",
                scenario = "During wedding preparations, your in-laws subtly hint that a luxury vehicle and jewelry must be gifted to maintain family prestige.",
                context = "Your parents are taking out high-interest loans in secret to comply out of fear of social embarrassment.",
                difficulty = "Foundational",
                suggestedDirections = "Intervene decisively, stop your parents taking loans, confront the demand transparently|Pretend not to notice and let parents bear the debt|Call off the wedding with public humiliation on social media|Demand an equal counter-gift from the other family",
                followUpQuestions = "What is the courage required to dismantle exploitative traditions that harm parents?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_fam_6",
                categoryId = "cat_family",
                title = "Supporting a Parent Who Verbally Abuses Your Spouse",
                scenario = "Your elderly parent living under your roof routinely insults your spouse's background, cooking, and character when you step out of the room.",
                context = "Your spouse is in tears and feeling unprotected in their own home.",
                difficulty = "Nuanced",
                suggestedDirections = "Set clear, respectful but firm household boundaries: abuse will not be tolerated|Tell spouse to endure it quietly because 'parents are like gods'|Verbally scream at parent and threaten to throw them on the street|Ignore the complaints hoping it will resolve on its own",
                followUpQuestions = "How can filial duty (seva) coexist with the sacred vow of protecting one's life partner?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_fam_7",
                categoryId = "cat_family",
                title = "Child Wishes to Pursue Fine Arts Instead of STEM",
                scenario = "Your teenage child exhibits profound talent in classical arts, but you and your family have invested heavily dreaming of an engineering or medical career.",
                context = "Relatives warn that an artistic path carries immense financial risk and instability.",
                difficulty = "Foundational",
                suggestedDirections = "Support their authentic vocation while helping them build pragmatic life and financial literacy|Force them into engineering against their will 'for their own good'|Cut off financial support if they don't follow your career command|Indifferently let them drop out of basic schooling",
                followUpQuestions = "Is a parent's duty molding a child into their own ambition, or nurturing the unique dharma (swadharma) of the soul?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_fam_8",
                categoryId = "cat_family",
                title = "Sharing Cost of Parents' Medical Treatment Among Siblings",
                scenario = "Your parent requires an expensive emergency surgery. You earn significantly more than your two siblings, who can barely afford basic household expenses.",
                context = "One sibling demands splitting the bill exactly in three equal parts, which would push them into debt.",
                difficulty = "Foundational",
                suggestedDirections = "Offer to shoulder the larger proportion graciously according to financial capacity without shaming them|Insist on an exact mathematical split regardless of their bankruptcy|Pay everything but constantly remind them of their debt in family dinners|Refuse to pay until all siblings contribute equally",
                followUpQuestions = "Does fairness mean strict mathematical equality, or compassionate equity based on capacity?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_fam_9",
                categoryId = "cat_family",
                title = "Estranged Parent Seeking Financial Shelter in Old Age",
                scenario = "A parent who abandoned you during your childhood returns thirty years later, ill and broke, asking you to house and feed them.",
                context = "You grew up facing hardship without their emotional or financial presence.",
                difficulty = "Profound",
                suggestedDirections = "Ensure they have humane shelter and basic medical care out of universal duty, without forcing emotional intimacy|Reject them at the door and mock their misfortune|Welcome them as if nothing happened and give them control over your finances|File legal cases to seek retroactive child support",
                followUpQuestions = "Can one perform dharmic duty towards an estranged parent from a stance of dignified compassion rather than bitter vengeance?"
            )
        )

        // 4. ANGER & REVENGE (9 scenarios)
        list.add(
            QuestionEntity(
                id = "q_ang_1",
                categoryId = "cat_anger",
                title = "Public Defamation on Social Media",
                scenario = "An acquaintance posts an inflammatory, distorted video of an argument you had, misrepresenting you as a hateful person. The post goes viral.",
                context = "You are receiving vicious hate messages and your impulse is to release their private scandalous messages in retaliation.",
                difficulty = "Nuanced",
                suggestedDirections = "Issue a calm, factual, documented clarification; pursue legal remedies without engaging in reciprocal mudslinging|Leak their private scandals online to destroy their reputation back|Hire cyber trolls to harass them relentlessly|Rage violently in public comments",
                followUpQuestions = "When Lakshmana was consumed by fury over unfair treatment, how did Rama redirect that energy towards righteous restraint?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_ang_2",
                categoryId = "cat_anger",
                title = "Neighbor Continuously Encroaching on Your Property",
                scenario = "Your neighbor repeatedly parks their vehicle blocking your driveway, damages your garden hedge, and scoffs when you ask them politely to stop.",
                context = "You feel rising fury and are tempted to vandalize their vehicle in the dead of night.",
                difficulty = "Foundational",
                suggestedDirections = "Document incidents systematically with video, engage neighborhood council and municipal authorities formally|Damage their vehicle or slash their tires secretly in retaliation|Confront them physically with weapons|Accept the abuse and park two blocks away",
                followUpQuestions = "How do you defend your rightful boundaries firmly through law and order without stooping to petty vengeance?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_ang_3",
                categoryId = "cat_anger",
                title = "Road Rage Incident with an Aggressive Driver",
                scenario = "A driver cuts you off aggressively, brakes dangerously, rolls down their window, spits at your vehicle, and hurls vile abuses.",
                context = "You have family in your car and your adrenaline is surging to chase them down.",
                difficulty = "Foundational",
                suggestedDirections = "Take a deep breath, prioritize passengers' safety, memorize plate, and let them speed away|Chase them aggressively to force their car off the road|Step out at next traffic signal to engage in a physical brawl|Post their license plate online calling for mob vigilantes",
                followUpQuestions = "What is the supreme value of self-restraint (samyama) when momentary ego tempts us into life-threatening folly?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_ang_4",
                categoryId = "cat_anger",
                title = "A Former Partner Ruining Your New Relationship",
                scenario = "Your ex-partner contacts your new fiancé, spreading twisted stories and intimate photos to sabotage your upcoming wedding out of spite.",
                context = "Your new partner is bewildered and you feel blinding rage.",
                difficulty = "Nuanced",
                suggestedDirections = "Have a transparent, vulnerable conversation with fiancé, report illegal photo sharing to police immediately|Share the ex-partner's intimate details publicly to ruin them|Threaten violence against the ex-partner|Break off the engagement in shame",
                followUpQuestions = "How do you handle acute malice from a former companion while maintaining moral dignity?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_ang_5",
                categoryId = "cat_anger",
                title = "Cheated Out of a Lucrative Business Deal by a Friend",
                scenario = "A childhood friend cuts you out of an enterprise you co-founded, using legal loopholes to retain 100% of the intellectual property.",
                context = "You are broke while they just closed a multi-million dollar funding round.",
                difficulty = "Profound",
                suggestedDirections = "Retain legal counsel to contest rights methodically, rebuild your own career with clean hands, refuse obsession with bitterness|Obsessively plot their downfall and vandalize their office|Wallow in depression and abandon all creative ambitions|Bribe inspectors to shut down their company",
                followUpQuestions = "How did Rama deal with the loss of Ayodhya's kingdom—by plotting bitter revenge, or by transforming exile into a glorious mission of dharma?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_ang_6",
                categoryId = "cat_anger",
                title = "Spiteful False Negative Review Destroying Small Business",
                scenario = "A customer who was refused a free service writes five fake 1-star reviews claiming they saw pests and experienced food poisoning in your family restaurant.",
                context = "Your revenue drops 40% in two weeks and you know the customer's identity and workplace.",
                difficulty = "Nuanced",
                suggestedDirections = "Reply publicly with polite documentation, hygiene audit certificates, and pursue platform dispute / legal cease-and-desist|Call their employer to fabricate allegations against them to get them fired|Send threatening thugs to their residence|Close the restaurant in defeat",
                followUpQuestions = "How can a righteous person counteract malicious falsehood with truth and dignity rather than reciprocal slander?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_ang_7",
                categoryId = "cat_anger",
                title = "Teacher or Coach Demeaning Your Child in Front of Peers",
                scenario = "A school coach publicly ridicules your 10-year-old child's physical coordination, calling them 'useless' until the child comes home weeping.",
                context = "You are trembling with protective parental fury.",
                difficulty = "Foundational",
                suggestedDirections = "Comfort and validate child first, request formal meeting with principal and athletic board with written testimony|Storm the field during practice and scream profanities at the coach|Tell the child to 'toughen up and stop crying'|Encourage the child to puncture the coach's car tires",
                followUpQuestions = "How does an emotionally mature guardian turn justified protective anger into structured accountability?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_ang_8",
                categoryId = "cat_anger",
                title = "Betrayed by a Trusted Mentor Who Stole Your Research",
                scenario = "Your academic advisor publishes your doctoral findings under their name alone, erasing your five years of painstaking laboratory work.",
                context = "The advisor sits on all hiring committees in your specialized field.",
                difficulty = "Profound",
                suggestedDirections = "Gather lab notebooks, version-controlled logs, and lodge an academic integrity grievance via university ethics committee|Physically destroy the lab equipment and data backups in rage|Surrender and leave academia entirely feeling cursed|Blackmail the advisor using unrelated personal gossip",
                followUpQuestions = "When authority figures betray their sacred duty, how do we pursue justice with cold clarity rather than self-destructive rage?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_ang_9",
                categoryId = "cat_anger",
                title = "Opponent in Sports Feigns Injury to Get You Disqualified",
                scenario = "In a tournament championship, an opponent acts dramatically after a clean tackle, convincing the referee you committed a flagrant foul and getting you ejected.",
                context = "Your team loses the final as a result, and the opponent winks at you from the podium.",
                difficulty = "Foundational",
                suggestedDirections = "Maintain composure, congratulate teammates, address sportsmanship with match commission through video review|Attack the opponent in the locker room hallway|Spit on the tournament trophy and refuse medal|Encourage fans to riot",
                followUpQuestions = "What constitutes true nobility of a warrior (Kshatriya Maryada) when cheated of victory?"
            )
        )

        // 5. TRUTH & HONESTY (9 scenarios)
        list.add(
            QuestionEntity(
                id = "q_tru_1",
                categoryId = "cat_truth",
                title = "Disclosing Major Hidden Flaw in a Home You Are Selling",
                scenario = "You are selling your house. You discover a recurring foundation water leak that only appears during heavy monsoon rains. The buyer's inspector missed it.",
                context = "Disclosing it will cost you $20,000 in price reduction, and you urgently need money for debt repayment.",
                difficulty = "Foundational",
                suggestedDirections = "Disclose the defect transparently in writing and negotiate fair repair adjustment|Conceal it and rush to close the sale before the rainy season|Blame the buyer's inspector for not finding it|Cover it with cosmetic quick-dry paint",
                followUpQuestions = "Does Satya (truthfulness) apply only when convenient, or is it tested precisely when honesty carries a financial loss?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_tru_2",
                categoryId = "cat_truth",
                title = "A White Lie to Prevent Severe Emotional Distress",
                scenario = "Your grandmother in critical hospital care asks if her estranged son visited her today. The son refused to come and hurled insults over the phone.",
                context = "Doctors warn that any severe grief or emotional shock could induce cardiac arrest.",
                difficulty = "Profound",
                suggestedDirections = "Gently reassure her of love and care, focusing on peace without brutal details|Tell her bluntly: 'Your son hates you and refused to come'|Lecture her on family karma|Fabricate an elaborate fantasy story of future visits",
                followUpQuestions = "How do the great rishis define Satya when blunt literal facts would inflict gratuitous suffering on a dying soul?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_tru_3",
                categoryId = "cat_truth",
                title = "Accidentally Overpaid by Cashier at Grocery Store",
                scenario = "You withdraw cash at a rural bank and realize the teller accidentally handed you two $100 bills stuck together instead of one.",
                context = "The bank has no CCTV in that corner, and the cashier is an overworked young clerk.",
                difficulty = "Foundational",
                suggestedDirections = "Immediately step back to the counter and return the excess cash|Pocket the money, rationalizing that banks make millions in profit|Give it away to a beggar on the street to feel virtuous|Keep it and wait to see if they call",
                followUpQuestions = "Is an act truly honest if no one is watching? What is the essence of internal integrity?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_tru_4",
                categoryId = "cat_truth",
                title = "Embellishing Credentials on a Resume to Escape Poverty",
                scenario = "You have the practical skill to do a job, but lack the formal Ivy League degree required by automated HR filters. Adding the degree ensures an interview.",
                context = "Your family is facing eviction.",
                difficulty = "Nuanced",
                suggestedDirections = "Showcase verified portfolio projects and reach out directly to hiring managers on merit|Falsify the diploma, believing survival excuses lying|Give up searching for better opportunities|Claim you earned a degree in an obscure foreign university",
                followUpQuestions = "Can a righteous career be built on a foundational falsehood?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_tru_5",
                categoryId = "cat_truth",
                title = "Covering Up a Team Colleague's Honest Mistake",
                scenario = "A junior developer accidentally dropped a production database table. You both worked all night and restored it from backup with zero permanent data loss.",
                context = "Company policy states that any production incident requires a root cause analysis naming responsible individuals.",
                difficulty = "Nuanced",
                suggestedDirections = "Submit incident report highlighting technical system vulnerabilities, while mentoring the junior to prevent recurrence|Throw the junior under the bus publicly to look flawless yourself|Falsify the logs to blame an external hacker|Cover it up entirely and hide the audit trail",
                followUpQuestions = "How do you uphold organizational truth while protecting learning juniors from disproportionate punitive blame?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_tru_6",
                categoryId = "cat_truth",
                title = "Witnessing a Hit-and-Run Committed by a Community Leader",
                scenario = "You see a prominent community philanthropist strike an empty parked vehicle at night and speed away without leaving a note.",
                context = "The philanthropist funds your local youth sports program. The damaged car belongs to a struggling delivery worker.",
                difficulty = "Nuanced",
                suggestedDirections = "Privately urge the leader to compensate the worker, while ensuring the worker receives the plate info if leader refuses|Report the leader directly to police as an objective witness|Stay quiet to protect the youth sports program funding|Help the leader wipe the paint marks off their car",
                followUpQuestions = "Does doing charitable works grant anyone immunity from basic honesty and accountability?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_tru_7",
                categoryId = "cat_truth",
                title = "Telling a Friend Their Creative Dream Has Fatal Flaws",
                scenario = "Your friend invested their entire life savings into an ill-conceived restaurant concept and asks for your honest critique before grand opening.",
                context = "Everyone else is giving polite false praise to avoid awkwardness.",
                difficulty = "Foundational",
                suggestedDirections = "Offer compassionate, thorough, constructive truth with actionable recommendations|Join the crowd in giving effusive false praise|Mock their food brutally in front of others|Tell them to abandon their dream immediately without explanation",
                followUpQuestions = "How is compassionate truth-telling (Priya-Satya) distinguished from both cowardice and cruelty?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_tru_8",
                categoryId = "cat_truth",
                title = "Fulfilling an Inconvenient Promise Made in Youth",
                scenario = "Ten years ago you promised a friend you would help them renovate their ancestral village home whenever they started. They call you today.",
                context = "You are now extremely busy with high-paying projects and traveling to the village is deeply inconvenient.",
                difficulty = "Profound",
                suggestedDirections = "Honor your commitment by scheduling time or contributing significant meaningful support|Make up false excuses about being sick or overwhelmed|Ghost their phone calls permanently|Tell them childhood promises don't count in adulthood",
                followUpQuestions = "Why did Lord Ram prioritize keeping a vow (Raghukula reeti) above kingdom, comfort, and crown?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_tru_9",
                categoryId = "cat_truth",
                title = "Admitting You Do Not Know in a High-Stakes Consultation",
                scenario = "A board of investors asks you a critical technical question outside your expertise. You could easily improvise a plausible buzzword answer.",
                context = "Admitting uncertainty might momentarily shake their perception of your omniscient authority.",
                difficulty = "Foundational",
                suggestedDirections = "State clearly: 'I don't have that precise data at hand; I will investigate and provide verified facts by tomorrow'|Bluff with confident jargon to preserve the illusion of mastery|Deflect the question by attacking the premise|Blame another team for withholding data",
                followUpQuestions = "How does intellectual humility reflect deep alignment with Satya and Viveka?"
            )
        )

        // Add scenarios for remaining categories: 6..12
        populateRemainingScenarios(list)

        return list
    }

    private fun populateRemainingScenarios(list: MutableList<QuestionEntity>) {
        // 6. MONEY & GREED (9 scenarios)
        list.add(
            QuestionEntity(
                id = "q_mon_1",
                categoryId = "cat_money",
                title = "Insider Trading Opportunity on Unannounced Acquisition",
                scenario = "You overhear in an elevator that a tech company is acquiring a small competitor at a 200% premium tomorrow morning.",
                context = "You could quietly buy stock through a personal brokerage and earn $50,000 without detectable audit trails.",
                difficulty = "Foundational",
                suggestedDirections = "Refuse to trade, recognizing unlawful unfair enrichment|Execute the trade, arguing 'everyone in finance does this'|Tip off friends so you don't trade directly|Sell short on the acquirer",
                followUpQuestions = "How does unearned gain pollute one's peace of mind and ethical foundation?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_mon_2",
                categoryId = "cat_money",
                title = "Price Gouging During an Acute Disaster",
                scenario = "A massive cyclone cuts off clean water supply in your district. You own the largest grocery warehouse with pallets of bottled water.",
                context = "Market demand would allow you to charge 10x regular prices and clear $100,000 profit in 48 hours.",
                difficulty = "Foundational",
                suggestedDirections = "Maintain fair standard prices and ration bottles so every family receives water|Increase prices to maximum to maximize market yield|Sell water only to the wealthiest bidders|Close warehouse and horde supplies for yourself",
                followUpQuestions = "What is Rajadharma and merchant ethics regarding profiting from human catastrophe?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_mon_3",
                categoryId = "cat_money",
                title = "Splitting a Windfall Lottery Ticket Bought Together",
                scenario = "You and an acquaintance casually chipped in $5 each to buy a lottery ticket. You held the ticket, which won $500,000. No written agreement was made.",
                context = "The acquaintance only remembers chipping in vaguely and didn't ask for a photo.",
                difficulty = "Foundational",
                suggestedDirections = "Honor the 50/50 mutual split immediately with joyful integrity|Keep the entire sum, claiming you bought a different ticket|Give them a tiny $5,000 gift while keeping $495,000|Hire a lawyer to intimidate them",
                followUpQuestions = "When unexpected wealth tests honor, how does true character shine?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_mon_4",
                categoryId = "cat_money",
                title = "Vendor Offers Luxury Vacation as 'Appreciation'",
                scenario = "A vendor bidding for your company's procurement contract offers you all-expense-paid luxury tickets for your family to Europe.",
                context = "They claim it is standard industry networking, but their bid is 15% more expensive than competitors.",
                difficulty = "Foundational",
                suggestedDirections = "Decline the gift immediately and declare it to compliance; evaluate bids strictly on merit|Accept the trip, telling yourself you will remain impartial|Accept the trip and award them the contract|Demand cash instead of tickets",
                followUpQuestions = "How does Maricha's golden deer illusion mirror corrupt favors dressed in glittering packaging?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_mon_5",
                categoryId = "cat_money",
                title = "Tax Loopholes vs Ethical Contribution to Public Infrastructure",
                scenario = "Your accountant proposes a legally aggressive offshore shell structure that reduces your tax bill from 35% to 2%, leaving roads and schools underfunded.",
                context = "The structure complies with literal technical loopholes but violates legislative intent.",
                difficulty = "Nuanced",
                suggestedDirections = "Pay fair, legitimate taxes while taking standard honest deductions|Use every aggressive offshore tax shelter possible to hoard wealth|Evade taxes completely and hide cash under mattress|Brag about non-payment on social media",
                followUpQuestions = "What is an individual's civic dharma towards the shared societal commons?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_mon_6",
                categoryId = "cat_money",
                title = "Inherited Money While Siblings Struggle in Poverty",
                scenario = "A distant aunt leaves you an unexpected bequest because she liked your letters. Your immediate cousins are struggling to afford basic schooling.",
                context = "You are legally entitled to every penny.",
                difficulty = "Nuanced",
                suggestedDirections = "Create an educational fund to support cousins' children out of family solidarity|Keep all funds and buy luxury watches|Flaunt the money in front of cousins to assert superiority|Lend it to cousins with predatory interest rates",
                followUpQuestions = "How does wealth stewardship reflect Lord Ram's ideal of power serving the vulnerable?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_mon_7",
                categoryId = "cat_money",
                title = "Selling a Product You Know is Harmful to Addictive Users",
                scenario = "You manage marketing for an online micro-gambling game engineered with psychological triggers that induce compulsive spending among youth.",
                context = "The role pays triple the industry average salary.",
                difficulty = "Profound",
                suggestedDirections = "Advocate ethical spending guardrails internally while actively transitioning to a wholesome industry|Maximize gambling addict mechanics to hit revenue bonuses|Blame users for having weak willpower|Encourage minors to borrow parent credit cards",
                followUpQuestions = "Can wealth earned through deliberate exploitation of human vulnerabilities bring genuine peace?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_mon_8",
                categoryId = "cat_money",
                title = "Refunding a Customer After Warranty Expired on Defective Batch",
                scenario = "A customer's appliance fails 3 days after warranty expiry. Internal engineering reports show the entire component batch had a planned obsolescence defect.",
                context = "Denying the claim saves your department $300 warranty costs.",
                difficulty = "Foundational",
                suggestedDirections = "Honor the repair or replacement voluntarily as an act of fairness and brand integrity|Reject the claim citing strict calendar cutoff dates|Charge them double for replacement parts|Mock the customer for not buying extended warranty",
                followUpQuestions = "Does business righteousness look only at technical loopholes, or at fundamental fairness?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_mon_9",
                categoryId = "cat_money",
                title = "Giving Charity for Tax Deductions and Social Vanity",
                scenario = "You are invited to a gala where your company wants you to pledge $50,000 solely so your name is engraved on a bronze wall for publicity.",
                context = "A local small orphanage down the road needs $10,000 for food with zero publicity.",
                difficulty = "Nuanced",
                suggestedDirections = "Direct meaningful resources to real grassroots needs quietly without seeking vanity applause|Give only to the gala for maximum social status and prestige|Refuse both and keep all money|Demand a larger plaque at the orphanage before giving",
                followUpQuestions = "What is the distinction between sattvic dana (pure unpublicized giving) and rajasic dana (giving for ego)?"
            )
        )

        // 7. LEADERSHIP & JUSTICE (9 scenarios)
        list.add(
            QuestionEntity(
                id = "q_lead_1",
                categoryId = "cat_leadership",
                title = "Disciplining a Star Performer Who Verbally Abuses Juniors",
                scenario = "Your top sales executive generates 40% of company revenue, but routinely yells profanities and creates terror among junior administrative staff.",
                context = "Firing or reprimanding them might cause revenue to dip next quarter.",
                difficulty = "Nuanced",
                suggestedDirections = "Enforce zero-tolerance for abuse, require behavioral transformation, and exit them if mistreatment continues|Ignore the abuse because their revenue makes them indispensable|Tell junior staff to develop thick skin|Fire the junior who complained to appease the star",
                followUpQuestions = "How did Lord Ram balance strength with universal dignity for all beings, down to the humblest squirrel?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_lead_2",
                categoryId = "cat_leadership",
                title = "Promoting a Personal Friend vs Superior Competitor",
                scenario = "You have an executive opening. Candidate A is your loyal college friend. Candidate B has significantly superior domain competence and team support.",
                context = "Your friend is counting on you for their career revival.",
                difficulty = "Foundational",
                suggestedDirections = "Appoint Candidate B based on objective merit, while supporting your friend with honest career counsel elsewhere|Appoint your friend to show loyalty, regardless of competence|Cancel the position to avoid choosing|Ask Candidate B to withdraw their application",
                followUpQuestions = "Why is nepotism the antithesis of Rajadharma and true justice?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_lead_3",
                categoryId = "cat_leadership",
                title = "Taking Full Responsibility for a Team Failure",
                scenario = "A product launch failed due to an oversight by your team members. The executive board is demanding to know whose head will roll.",
                context = "You are the department vice president.",
                difficulty = "Foundational",
                suggestedDirections = "Take full leadership accountability publicly, shield your team, and conduct private constructive retrospectives|Blame individual juniors in front of the board to preserve your standing|Resign immediately without doing post-mortem|Blame external vendors falsely",
                followUpQuestions = "What defines the character of a protector (Rakshaka) when adversity strikes?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_lead_4",
                categoryId = "cat_leadership",
                title = "Granting Asylum to a Defector from a Fierce Competitor",
                scenario = "A senior strategist from your fiercest corporate rival defects, alleging ethical corruption at the rival firm, and seeks employment at your company.",
                context = "Your executive team suspects they might be a corporate spy sent to sabotage you.",
                difficulty = "Profound",
                suggestedDirections = "Conduct prudent vetting, but offer protection and fair opportunity if their conscience is sincere|Reject them with paranoia without hearing their case|Exploit their secrets and then discard them|Turn them over to the rival company for a bounty",
                followUpQuestions = "How does Rama granting sanctuary (Sharanagati) to Vibhishana guide leadership in extending magnanimous trust?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_lead_5",
                categoryId = "cat_leadership",
                title = "Executing Painful Company Layoffs with Dignity",
                scenario = "Due to macroeconomic shifts, your startup must let go of 20% of staff to survive. Advisors urge doing it via sudden email and locking laptops instantly.",
                context = "You want to treat workers like human beings with dignity.",
                difficulty = "Nuanced",
                suggestedDirections = "Deliver news transparently with generous severance, career placement, and personal empathy|Lock laptop access at 8 AM with an automated cold bot email|Blame the laid off workers for lack of talent|Take a massive executive bonus right after laying them off",
                followUpQuestions = "How does a leader uphold compassion (karuna) during painful institutional necessities?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_lead_6",
                categoryId = "cat_leadership",
                title = "Standing Firm Against Popular Majority Injustice",
                scenario = "Your community association votes by an 80% majority to ban delivery workers and house-helps from using elevators, demanding they take stairwells.",
                context = "You are the association president.",
                difficulty = "Foundational",
                suggestedDirections = "Veto the discriminatory rule firmly, educating the community on basic human dignity and constitutional equality|Enforce the ban because 'democracy means majority rules'|Resign to let them do whatever they want|Add extra penalties for delivery workers",
                followUpQuestions = "Is justice determined by headcounts, or by universal moral principles (Sanatana Dharma)?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_lead_7",
                categoryId = "cat_leadership",
                title = "Balancing Mercy and Punishment for an Embezzling Treasurer",
                scenario = "A community treasurer was caught diverting funds to pay for their child's emergency surgery. The money was paid back with interest a week later.",
                context = "Rules mandate lifetime expulsion and criminal prosecution.",
                difficulty = "Nuanced",
                suggestedDirections = "Remove them from financial custody firmly, while contextualizing human compassion and avoiding vindictive prosecution|Prosecute them with maximum criminal penalties without mercy|Praise them and let them keep managing the treasury|Cover it up completely and falsify the ledger",
                followUpQuestions = "How did ancient kings balance danda (punishment) with karuna (compassion) in context-sensitive jurisprudence?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_lead_8",
                categoryId = "cat_leadership",
                title = "Honoring an Adversary's Legitimate Achievements",
                scenario = "Your political opponent successfully passes a public healthcare bill that genuinely helps millions. Your party leadership demands attacking the bill.",
                context = "You are a party spokesperson.",
                difficulty = "Foundational",
                suggestedDirections = "Acknowledge the public benefit of the healthcare measures while offering constructive improvements|Fabricate lies claiming the bill is poison|Refuse to speak on healthcare|Claim credit for the opponent's work",
                followUpQuestions = "How did Rama command Lakshmana to learn statesmanship from the dying Ravana without petty spite?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_lead_9",
                categoryId = "cat_leadership",
                title = "Whistleblower Within Your Own Department",
                scenario = "A junior whistleblower in your team reveals you made an inadvertent regulatory misstatement six months ago on an environmental filing.",
                context = "You could quietly bury their memo without anyone noticing.",
                difficulty = "Profound",
                suggestedDirections = "Commend the whistleblower, report the misstatement voluntarily to the regulator, and self-correct|Fire the whistleblower for insubordination|Alter the historical documents to cover up the mistake|Threaten the junior's career",
                followUpQuestions = "Does a true leader welcome accountability, or defend their personal infallibility?"
            )
        )

        // 8. FRIENDSHIP & LOYALTY (9 scenarios)
        list.add(
            QuestionEntity(
                id = "q_frn_1",
                categoryId = "cat_friendship",
                title = "Friend Caught Cheating on Professional Bar Exam",
                scenario = "Your lifelong best friend brings smuggled answers into the medical/legal licensing exam and asks you to stay silent.",
                context = "If they qualify, they will be licensed to treat patients / represent clients.",
                difficulty = "Profound",
                suggestedDirections = "Confront friend privately, urge them to withdraw voluntarily, and refuse to lie if questioned by examiners|Cheer them on and help them copy answers|Report them maliciously to ensure they are barred for life|Abandon the friendship without addressing the issue",
                followUpQuestions = "Does true loyalty mean enabling a friend's dangerous deceit, or holding them to righteousness?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_frn_2",
                categoryId = "cat_friendship",
                title = "Standing by a Bankrupt Friend When Others Flee",
                scenario = "A friend whose lavish parties everyone attended suffers catastrophic business failure and is ostracized by former social peers.",
                context = "Associating with them offers zero career benefit.",
                difficulty = "Foundational",
                suggestedDirections = "Stand steadfastly by their side, offer practical support, emotional solace, and respect|Ghost them like everyone else did|Mock their failure on social media|Lend them money at extortionate rates",
                followUpQuestions = "How did Rama treat the exiled, penniless Sugriva—as an equal sovereign and eternal brother?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_frn_3",
                categoryId = "cat_friendship",
                title = "Calling Out a Friend's Hateful Bigotry in Private",
                scenario = "At a private dinner, a close friend makes deeply cruel, derogatory remarks about an underprivileged minority group.",
                context = "Speaking up will make the dinner awkward, but remaining silent feels like complicity.",
                difficulty = "Foundational",
                suggestedDirections = "Calmly and firmly challenge the remarks: 'I respect you, but that prejudice is wrong and unacceptable'|Laugh along to keep the mood light|Sever contact forever without explaining why|Film them secretly and post it online",
                followUpQuestions = "What is the duty of Mitra-Dharma in helping a friend overcome moral blindness?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_frn_4",
                categoryId = "cat_friendship",
                title = "Friend Asks for a Perjury Testimony in Custody Dispute",
                scenario = "Your friend asks you to testify in court that you witnessed their ex-spouse abusing their child, even though you never saw any such incident.",
                context = "They claim: 'It is the only way I can win custody of my child.'",
                difficulty = "Profound",
                suggestedDirections = "Refuse firmly to perjure yourself on the stand, while offering ethical emotional and child-care support|Lie on the witness stand under oath for your friend|Report your friend to Child Protective Services immediately|Cut all contact abruptly",
                followUpQuestions = "Can falsehood ever serve the true long-term welfare of a child?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_frn_5",
                categoryId = "cat_friendship",
                title = "Friend Joins a Competing Rival Enterprise",
                scenario = "Your closest collaborator leaves your firm to take a lucrative role at your most aggressive competitor.",
                context = "Colleagues tell you to treat them as an enemy and cut all social ties.",
                difficulty = "Foundational",
                suggestedDirections = "Celebrate their professional success, keep competitive trade secrets strictly walled, preserve warm human friendship|Treat them with bitter hostility and spread rumors about their disloyalty|Try to sabotage their new job|Leak false company info to them to mislead their new employer",
                followUpQuestions = "Can competitive rivalry exist alongside deep personal dignity and goodwill?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_frn_6",
                categoryId = "cat_friendship",
                title = "Intervening in a Friend's Dangerous Addiction",
                scenario = "A close friend is spiraling into severe alcohol addiction, neglecting their children and driving intoxicated.",
                context = "They react with fury whenever anyone mentions their drinking.",
                difficulty = "Nuanced",
                suggestedDirections = "Organize a loving, structured family intervention with addiction specialists, refusing to enable the denial|Keep drinking with them to be 'non-judgmental'|Cut ties and say 'it is their personal life'|Mock them when they are intoxicated",
                followUpQuestions = "What is the courage required to risk a friend's temporary displeasure to save their life?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_frn_7",
                categoryId = "cat_friendship",
                title = "Borrowing Money from a Friend and Facing Financial Delay",
                scenario = "You borrowed $5,000 from a friend promising repayment in 3 months. Unexpected hospital bills leave you unable to pay on time.",
                context = "The deadline is tomorrow.",
                difficulty = "Foundational",
                suggestedDirections = "Reach out proactively before the deadline, explain transparently, offer a revised schedule with interest|Avoid their calls and hope they forget|Blame them for asking about their own money|Take out loan shark debt to pay them without explanation",
                followUpQuestions = "How does proactive truthfulness preserve the sanctity of trust between companions?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_frn_8",
                categoryId = "cat_friendship",
                title = "Friend Discloses Attraction to Your Spouse",
                scenario = "During a private moment, a close friend confesses they have harbored romantic feelings for your spouse for months, but never acted on it.",
                context = "They claim they are sharing to be honest and ask what to do.",
                difficulty = "Nuanced",
                suggestedDirections = "Appreciate the candor, but establish strict boundaries and distance to protect marital sanctity and allow feelings to clear|Attack the friend with physical violence|Tell spouse to flirt with them as a test|Humiliate the friend in front of your peer group",
                followUpQuestions = "How do you protect sacred relational boundaries with self-restraint and clear resolve?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_frn_9",
                categoryId = "cat_friendship",
                title = "Celebrating a Friend Who Won an Award You Desperately Wanted",
                scenario = "You and your peer worked on parallel research. They won the prestigious national prize you dreamed of since childhood.",
                context = "You feel a sudden pang of jealousy and resentment.",
                difficulty = "Foundational",
                suggestedDirections = "Conquer inner envy, congratulate them with genuine warmth, and rejoice in their accomplishment|Give backhanded compliments: 'You got lucky with easy judges'|Refuse to attend the ceremony and sulk at home|Whisper rumors that the selection committee was bribed",
                followUpQuestions = "How does conquering Asuya (jealousy) elevate the human spirit towards divine equanimity?"
            )
        )

        // 9. FEAR & FAILURE (8 scenarios)
        list.add(
            QuestionEntity(
                id = "q_fear_1",
                categoryId = "cat_fear",
                title = "Startup Bankruptcy and Social Shame",
                scenario = "Your business venture went bankrupt after four years of tireless effort, exhausting your personal savings. Relatives whisper gossip about your failure.",
                context = "You feel paralyzed by humiliation and tempted to hide from the world.",
                difficulty = "Foundational",
                suggestedDirections = "Own the failure with dignified head held high, analyze lessons, fulfill vendor debts responsibly, rebuild anew|Hide in shame and blame external fate or economy|Resort to alcohol to numb feelings of worthlessness|Start a scam enterprise to make quick comeback money",
                followUpQuestions = "How did Jatayu teach that honor lies in the righteousness of the fight, not in avoiding defeat?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_fear_2",
                categoryId = "cat_fear",
                title = "Speaking Up in an Executive Room Paralyzed by Fear",
                scenario = "The CEO proposes a catastrophic strategy that everyone in the room knows will bankrupt the company, but no one speaks out of fear of being fired.",
                context = "You are the youngest director present.",
                difficulty = "Nuanced",
                suggestedDirections = "Raise structured, respectful, data-backed concerns with constructive alternatives|Stay silent like everyone else and watch the ship sink|Nod eagerly and praise the flawed plan|Whisper sarcastic jokes to your neighbor during the presentation",
                followUpQuestions = "How does moral courage (Dhairya) overcome institutional groupthink and cowardice?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_fear_3",
                categoryId = "cat_fear",
                title = "Chronic Imposter Syndrome Paralyzing Career Growth",
                scenario = "You are offered a monumental leadership role overseeing 500 people. You feel terrified that you are an imposter who will be exposed.",
                context = "Refusing the role will stagnate your potential.",
                difficulty = "Foundational",
                suggestedDirections = "Accept with humble dedication, view leadership as service (seva) rather than ego, build a wise advisory circle|Decline out of terror and stay in comfort zone forever|Accept with arrogant bluster to mask inner insecurity|Sabotage the promotion intentionally",
                followUpQuestions = "How did Hanuman overcome self-doubt when Jambavan reminded him of his latent divine strength before crossing the ocean?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_fear_4",
                categoryId = "cat_fear",
                title = "Leaving a Golden Handcuff Job to Pursue Meaningful Calling",
                scenario = "You have spent 15 years in a lucrative corporate job that is soul-draining. You have an opportunity to lead a grassroots rural education trust.",
                context = "Your luxury lifestyle will shrink significantly.",
                difficulty = "Nuanced",
                suggestedDirections = "Plan financial transition prudently and step courageously towards higher service and purpose|Stay in the gilded cage forever complaining of emptiness|Quit on impulse without any emergency fund for your dependents|Mock those who engage in social service",
                followUpQuestions = "What is the value of simplifying outer wants to serve a noble dharmic calling?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_fear_5",
                categoryId = "cat_fear",
                title = "Facing a Severe Health Diagnosis with Fortitude",
                scenario = "You receive news of a chronic autoimmune illness requiring permanent lifestyle changes and medical management.",
                context = "Your immediate reaction is despair: 'Why me? What did I do wrong?'",
                difficulty = "Profound",
                suggestedDirections = "Accept the reality with stoic grace, adopt disciplined healing routines, cherish life's blessings, and inspire others|Descend into bitter nihilism and lash out at loved ones|Deny medical science and pursue dangerous quack treatments|Surrender all hope and give up on daily living",
                followUpQuestions = "How does equanimity in pleasure and pain (Sama-duhkha-sukha) fortify the warrior soul?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_fear_6",
                categoryId = "cat_fear",
                title = "Admitting You Made a Blunder in Front of Your Team",
                scenario = "A strategy you passionately championed resulted in a major client cancellation. The team is gathered in the conference room.",
                context = "You could subtly shift blame onto the market conditions.",
                difficulty = "Foundational",
                suggestedDirections = "Stand up and say clearly: 'This was my misjudgment; here is what we learn and how we fix it'|Blame macroeconomic interest rates and competitors|Blame the junior presentation designer|Pretend the client was unprofitable anyway",
                followUpQuestions = "Why does acknowledging error enhance true authority rather than diminish it?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_fear_7",
                categoryId = "cat_fear",
                title = "Fear of Aging and Loss of Physical Youth",
                scenario = "You notice gray hair, wrinkles, and diminished athletic stamina. You feel rising anxiety and temptation to spend obsessively on artificial youth treatments.",
                context = "Society equates worth with youthful appearance.",
                difficulty = "Foundational",
                suggestedDirections = "Embrace the natural seasons of life, cultivating inner wisdom, spiritual depth, and mentorship|Obsessively chase cosmetic surgeries to deny mortality|Fall into depression about physical changes|Belittle younger people out of envy",
                followUpQuestions = "How do the stages of life (Ashrama dharma) celebrate the transition from outer conquest to inner illumination?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_fear_8",
                categoryId = "cat_fear",
                title = "Taking a Stand in a Hostile Town Hall Meeting",
                scenario = "A developer plans to clear an ancient community forest grove to build luxury condos. The town hall is packed with hired hecklers shouting down opposition.",
                context = "Stepping to the microphone will draw loud jeering.",
                difficulty = "Nuanced",
                suggestedDirections = "Step to the microphone with dignified calm, articulate environmental and cultural facts clearly without shouting|Leave quietly to avoid discomfort|Shout profanities back at the hecklers|Vandalize the developer's machinery at night",
                followUpQuestions = "How did Angada maintain unshakable poise in the hostile court of Ravana?"
            )
        )

        // 10. DIFFICULT MORAL DECISIONS (8 scenarios)
        list.add(
            QuestionEntity(
                id = "q_mor_1",
                categoryId = "cat_moral",
                title = "Allocating a Single Ventilator in a Pandemic Crisis",
                scenario = "As an ER doctor, you have one remaining ICU bed. Two patients arrive simultaneously: a 25-year-old criminal and an 80-year-old beloved community teacher.",
                context = "Medical guidelines suggest prioritizing highest statistical years of life saved, but your heart aches.",
                difficulty = "Profound",
                suggestedDirections = "Follow established clinical triage ethics impartially while providing palliative comfort to the other with deep compassion|Auction the bed to whoever pays more|Make decision based on personal religious preference|Flee the ER to avoid deciding",
                followUpQuestions = "How does one act righteously when every available path carries sorrow?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_mor_2",
                categoryId = "cat_moral",
                title = "Protecting an Undocumented Honest Worker from Deportation",
                scenario = "An undocumented immigrant who has worked diligently for ten years and fed your family bakery faces sudden random immigration raids.",
                context = "Hiding them violates immigration statute; turning them in tears apart their newborn child.",
                difficulty = "Profound",
                suggestedDirections = "Provide legal counsel, humanitarian sanctuary, and lawful representation to safeguard the family's human dignity|Hand them over to authorities to collect an informant reward|Exploit their vulnerability to cut their wages in half|Fire them onto the street with zero pay",
                followUpQuestions = "What is the higher cosmic dharma when human statutory laws clash with core compassion for the defenseless?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_mor_3",
                categoryId = "cat_moral",
                title = "Executing a Dying Pet with Dignity vs Extending Suffering",
                scenario = "Your companion dog of 15 years has terminal bone cancer and is howling in unmanageable agony despite maximum painkillers.",
                context = "Your attachment makes you want to keep them alive for another week.",
                difficulty = "Nuanced",
                suggestedDirections = "Choose compassionate veterinary release in your arms, placing their relief from agony above your selfish attachment|Keep them in agony to delay your personal grief|Abandon the dog at a shelter to avoid witnessing it|Blame the vet for not working miracles",
                followUpQuestions = "Can true love demand letting go of what we cherish to end their suffering?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_mor_4",
                categoryId = "cat_moral",
                title = "Accepting a Lucrative Defense Contract for Lethal Drones",
                scenario = "Your software AI startup is offered a $10M defense contract to optimize autonomous lethal targeting algorithms with minimal human oversight.",
                context = "Without this money your company will close; with it you become wealthy.",
                difficulty = "Profound",
                suggestedDirections = "Decline autonomous lethal targeting on moral grounds, pivoting to civilian and defensive verification technologies|Accept enthusiastically, claiming 'someone else will build it anyway'|Take the money and build intentional bugs to sabotage it|Brag about military power",
                followUpQuestions = "What is the ethical responsibility of a scientist or technologist for the destructive weapons they unleash?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_mor_5",
                categoryId = "cat_moral",
                title = "Breaking a Sealed Promise to Stop a Friend's Suicide",
                scenario = "A friend makes you swear a sacred oath of secrecy, then reveals they have acquired lethal poison and intend to end their life tonight.",
                context = "You promised never to tell anyone.",
                difficulty = "Profound",
                suggestedDirections = "Break the oath immediately and alert emergency medical services, family, and crisis intervention to save their life|Keep the promise of secrecy and let them die|Wait until tomorrow morning to check|Lecture them on sin without calling help",
                followUpQuestions = "Does dharma allow breaking a verbal vow when the supreme preservation of human life is at stake?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_mor_6",
                categoryId = "cat_moral",
                title = "Two Competing Worthy Causes Requesting Your Sole Donation",
                scenario = "You have $1,000 to donate: one goes to feed starving children in a war zone today; the other funds cancer research that could save millions in twenty years.",
                context = "You cannot fund both.",
                difficulty = "Nuanced",
                suggestedDirections = "Reflect deeply, allocate with pure heart without paralysis, recognizing both are sacred offerings|Refuse to donate to either because choosing is too stressful|Demand both charities compete in a humiliating pitch contest|Spend the money on personal luxury instead",
                followUpQuestions = "How does Nishkama Karma guide action when multiple virtuous paths compete?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_mor_7",
                categoryId = "cat_moral",
                title = "Returning a Lost Artifact to an Indigenous Tribe vs National Museum",
                scenario = "You inherit a sacred ceremonial relic looted during colonial warfare. A national museum offers $200,000; the original indigenous tribe asks for repatriation.",
                context = "The museum argues it will preserve it behind glass for millions of visitors.",
                difficulty = "Nuanced",
                suggestedDirections = "Repatriate the sacred artifact to its rightful indigenous guardians with reverence and humility|Sell it to the museum for personal profit|Auction it to private billionaire collectors|Keep it on your mantelpiece as a trophy",
                followUpQuestions = "What is the restorative justice owed to cultures that suffered unjust plunder?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_mor_8",
                categoryId = "cat_moral",
                title = "Publishing Dangerous Whistleblower Secrets That Threaten Soldiers",
                scenario = "An investigative journalist receives documents showing government corruption, but publishing unredacted documents will expose covert field operatives to ambush.",
                context = "The press deadline is imminent.",
                difficulty = "Profound",
                suggestedDirections = "Expose the corruption fully while carefully redacting names and operational details that put human lives in danger|Publish unredacted documents for sensational clicks regardless of bloodshed|Bury the corruption story entirely|Sell the secrets to foreign adversaries",
                followUpQuestions = "How does Viveka balance the duty of transparency with the duty of preventing bloodshed?"
            )
        )

        // 11. SOCIETY & INJUSTICE (8 scenarios)
        list.add(
            QuestionEntity(
                id = "q_soc_1",
                categoryId = "cat_society",
                title = "Intervening in Public Harassment on a Subway Train",
                scenario = "A passenger is loudly intimidating and harassing a solitary young woman on a crowded subway carriage. Other commuters look away pretending not to notice.",
                context = "Intervening might draw the aggressor's hostility towards you.",
                difficulty = "Foundational",
                suggestedDirections = "Step forward calmly, engage the victim directly ('Excuse me, can you sit with us?'), alert transit police, de-escalate|Stare at your smartphone and ignore the situation|Start screaming profanities and trigger a knife fight|Film it for TikTok views without helping",
                followUpQuestions = "How did Jatayu embody the absolute duty to defend the vulnerable, even when alone against danger?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_soc_2",
                categoryId = "cat_society",
                title = "Apartment Society Discriminates Against Domestic Workers",
                scenario = "Your housing society management proposes a rule forbidding house-helps and drivers from sitting on garden benches in common areas.",
                context = "Many influential residents support the rule to 'maintain exclusivity.'",
                difficulty = "Foundational",
                suggestedDirections = "Organize progressive residents, challenge the exclusionary rule publicly, affirm equal dignity in shared spaces|Endorse the ban to fit in with wealthy neighbors|Complain quietly in private but vote for the ban|Avoid the annual meeting entirely",
                followUpQuestions = "How did Lord Ram embrace Guha the Nishada and Shabari to demolish aristocratic prejudice?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_soc_3",
                categoryId = "cat_society",
                title = "Reporting a Powerful Neighbor's Domestic Violence",
                scenario = "You hear harrowing cries of domestic abuse and violence coming from your influential neighbor's apartment every weekend.",
                context = "The neighbor is a high-ranking police commissioner who could make life difficult for you.",
                difficulty = "Nuanced",
                suggestedDirections = "Contact specialized domestic violence hotlines, record dates/times, file anonymous state welfare reports|Ignore it because 'family matters are private'|Confront the abusive commissioner alone with fists|Tell the victim she must endure her karma",
                followUpQuestions = "What is the boundary between non-interference and moral cowardice when human beings are being battered?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_soc_4",
                categoryId = "cat_society",
                title = "Facing Peer Pressure to Join an Unjust Social Boycott",
                scenario = "Your merchant guild demands all store owners boycott a small family shop because they belong to a different religious community.",
                context = "Non-compliance risks having your own shop vandalized.",
                difficulty = "Nuanced",
                suggestedDirections = "Refuse to participate in communal boycotts, stand in solidarity with the family, seek lawful protection|Comply with the boycott to protect your personal business revenue|Join in attacking the family shop|Flee the city in silence",
                followUpQuestions = "How does a seeker of Rama-rajya resist communal hysteria and uphold Sanatana fellowship?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_soc_5",
                categoryId = "cat_society",
                title = "A Factory Polluting the Town's Drinking Water Well",
                scenario = "The town's primary employer is quietly discharging toxic chemicals into the municipal river. The town mayor covers it up to protect tax revenues.",
                context = "Local children are developing respiratory and skin ailments.",
                difficulty = "Nuanced",
                suggestedDirections = "Gather independent water samples, mobilize citizens, file environmental legal actions, bring media scrutiny|Keep drinking bottled water and say nothing|Threaten to blow up the factory|Blame the sick children's parents",
                followUpQuestions = "What is the stewardship duty towards Mother Earth (Bhumi Devi) and collective public health?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_soc_6",
                categoryId = "cat_society",
                title = "Standing Up for a Falsely Accused Colleague in Cancel Culture",
                scenario = "An internet mob begins a viral campaign to get a colleague fired over an out-of-context 5-second video clip that completely misrepresents what happened.",
                context = "Standing up for them risks the mob turning its fury onto you and your family.",
                difficulty = "Profound",
                suggestedDirections = "Publicly publish the unedited full video context, testify to their authentic character, advocate due process|Join the mob to prove your own purity|Ghost your colleague and unfriend them|Demand the company fire them without investigation",
                followUpQuestions = "Why is mob justice and hasty condemnation the supreme enemy of Nyaya (righteous justice)?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_soc_7",
                categoryId = "cat_society",
                title = "Providing Medical Care to an Enemy Soldier in War",
                scenario = "As an army surgeon, a severely wounded enemy soldier who fired at your squad is brought to your triage tent alongside a lightly injured friendly soldier.",
                context = "The enemy soldier will die in 10 minutes without immediate intervention.",
                difficulty = "Profound",
                suggestedDirections = "Triage based strictly on medical urgency: stabilize the dying enemy soldier first, honoring Hippocratic and Kshatriya Maryada|Let the enemy soldier bleed to death in revenge|Torture the wounded soldier for intelligence|Treat only friendly soldiers regardless of severity",
                followUpQuestions = "How did Rama command that Ravana receive full imperial honors and care once battle ceased?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_soc_8",
                categoryId = "cat_society",
                title = "Challenging Corruption at a Local Motor Vehicle Licensing Office",
                scenario = "An official refuses to process your driver's license application without a $50 cash bribe, telling you you will wait six months otherwise.",
                context = "Paying the bribe is fast and everyone else pays it without thinking.",
                difficulty = "Foundational",
                suggestedDirections = "Refuse the bribe firmly, report to anti-corruption vigilance helpline, use online grievance portals|Pay the bribe and complain on Facebook later|Scream at the clerk and break office glass|Abandon driving forever",
                followUpQuestions = "How does individual resistance to petty bribery rebuild the integrity of a nation?"
            )
        )

        // 12. SELF-DISCIPLINE & PERSONAL GROWTH (8 scenarios)
        list.add(
            QuestionEntity(
                id = "q_gro_1",
                categoryId = "cat_growth",
                title = "Overcoming Digital Addiction and Dopamine Distractions",
                scenario = "You spend 6 hours daily scrolling short-form social videos, neglecting your health, family conversations, and career aspirations.",
                context = "You feel chronic brain fog and self-loathing after every binge.",
                difficulty = "Foundational",
                suggestedDirections = "Institute disciplined digital fasting (tapasya), delete addictive apps, replace with sacred reading, physical exercise, and presence|Give up, declaring 'I am doomed in the modern age'|Buy a second phone to scroll more|Blame tech algorithms for your lack of willpower",
                followUpQuestions = "What is the power of Samyama (mastery over the senses) in reclaiming the sovereign mind?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_gro_2",
                categoryId = "cat_growth",
                title = "Managing Sudden Fame and Swelling Ego",
                scenario = "A creative project you launched unexpectedly went viral worldwide. Famous personalities flatter you and your social follower count exploded.",
                context = "You notice yourself becoming arrogant, impatient with old friends, and dismissive of constructive feedback.",
                difficulty = "Nuanced",
                suggestedDirections = "Cultivate conscious humility, practice seva (service) in anonymity, seek counsel of grounded mentors, remember transience of fame|Lean into your arrogance and declare yourself a genius|Discard old friends who don't have blue checkmarks|Demand special treatment everywhere you go",
                followUpQuestions = "How did Rama remain completely unaffected by coronation praise or exile banishment?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_gro_3",
                categoryId = "cat_growth",
                title = "Restraint of Speech in Heated Domestic Arguments",
                scenario = "During an argument with your spouse, a deeply cruel, cutting sentence comes to your tongue that would instantly devastate them.",
                context = "You are furious and want to 'win' the argument.",
                difficulty = "Foundational",
                suggestedDirections = "Bite your tongue, pause in silence, breathe deeply, and communicate constructive feelings without poison|Hurl the cutting insult to win the argument at all costs|Smash plates to demonstrate anger|Walk out for three weeks without word",
                followUpQuestions = "Why is Vak-Samyama (disciplined speech that is true, gentle, and beneficial) the mark of nobility?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_gro_4",
                categoryId = "cat_growth",
                title = "Maintaining Daily Spiritual Practice Amidst Overwhelming Work",
                scenario = "Your work calendar is packed with 12-hour shifts. You haven't meditated, exercised, or reflected in three months.",
                context = "You feel exhausted, irritable, and spiritually depleted.",
                difficulty = "Foundational",
                suggestedDirections = "Protect 20 sacred morning minutes non-negotiably for quiet reflection, prayer, and breathwork|Claim spirituality is for retired people only|Work 16 hours until you collapse into hospitalization|Spend free time doom-scrolling",
                followUpQuestions = "How did Rama and the sages sustain their daily Sandhyavandana and Tapasya even in the wilderness?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_gro_5",
                categoryId = "cat_growth",
                title = "Conquering Lust and Transgressing Boundaries in Private",
                scenario = "While traveling alone on a business trip, you meet someone attractive who invites you to their room. No one back home will ever find out.",
                context = "You have a committed loving marriage.",
                difficulty = "Foundational",
                suggestedDirections = "Politely and firmly decline, honor your marital sacred vow, and remove yourself from compromising situations|Accept the invitation, rationalizing that 'what happens on travel stays on travel'|Blame your spouse for not traveling with you|Engage in emotional infidelity instead",
                followUpQuestions = "How did Lord Ram maintain unbroken Maryada and loyalty to Sita (Eka-Patni-Vrata) across 14 years of trials?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_gro_6",
                categoryId = "cat_growth",
                title = "Dealing with Bitter Regret Over Past Mistakes",
                scenario = "Five years ago you made a catastrophic financial and relational error that cost you dearly. You constantly ruminate in self-flagellation.",
                context = "The guilt is preventing you from living in the present.",
                difficulty = "Profound",
                suggestedDirections = "Transform guilt into wisdom: atone where possible, accept your humanity, and channel lessons into righteous future service|Spend every day cursing yourself in despair|Deny you ever made a mistake and blame others|Commit new reckless mistakes out of self-hatred",
                followUpQuestions = "How did Ratnakara transform from a lost soul into Maharshi Valmiki through devotion to Rama Nama?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_gro_7",
                categoryId = "cat_growth",
                title = "Choosing Forbearance Over Snarky Intellectual One-Upmanship",
                scenario = "In a public lecture, an eager student asks a question with basic factual misunderstandings. You have the wit to deliver a devastating joke at their expense.",
                context = "The audience would laugh and applaud your quick wit.",
                difficulty = "Foundational",
                suggestedDirections = "Honor the student's curiosity, address the misconception gently with encouraging respect|Humiliate the student with a cutting public joke|Kick the student out of the lecture|Ignore them with visible contempt",
                followUpQuestions = "How does a wise person use intellect to illuminate others rather than burn them?"
            )
        )
        list.add(
            QuestionEntity(
                id = "q_gro_8",
                categoryId = "cat_growth",
                title = "Staying True to Ideals When the World Rewards Cynicism",
                scenario = "You observe unethical, dishonest, and aggressive people being promoted, enriched, and celebrated, while your quiet honesty feels unrewarded.",
                context = "You feel tempted to abandon your principles and adopt ruthless cynicism.",
                difficulty = "Profound",
                suggestedDirections = "Anchor deeply in the eternal truth: Dharma protects those who protect it (Dharmo rakshati rakshitah); outer glamor is fleeting, inner honor is immortal|Become corrupt and cutthroat like the rest|Give up on life and retreat into cynical bitterness|Complain endlessly about the decline of the world",
                followUpQuestions = "Why did Lord Ram choose forest thorns over Ayodhya's palace—because truth and righteous honor outshine all temporary earthly empires."
            )
        )
    }

    fun getAllRubrics(): List<ScoringRubricEntity> {
        // Return structured rubrics for all scenarios
        val rubrics = mutableListOf<ScoringRubricEntity>()
        val allScenarios = getAllScenarios()

        allScenarios.forEach { q ->
            val applicableDims = when (q.categoryId) {
                "cat_career" -> "satya,dharma,nyaya,samyama"
                "cat_relationships" -> "karuna,satya,samyama,nishtha"
                "cat_family" -> "dharma,karuna,satya,tyaga"
                "cat_anger" -> "samyama,viveka,karuna,nyaya"
                "cat_truth" -> "satya,dharma,viveka,dhairya"
                "cat_money" -> "satya,nyaya,samyama,viveka"
                "cat_leadership" -> "nyaya,karuna,dhairya,samyama"
                "cat_friendship" -> "nishtha,satya,karuna,viveka"
                "cat_fear" -> "dhairya,viveka,samyama,dharma"
                "cat_moral" -> "viveka,dharma,karuna,nyaya"
                "cat_society" -> "nyaya,dhairya,karuna,dharma"
                "cat_growth" -> "samyama,satya,viveka,dharma"
                else -> "dharma,satya,karuna,viveka"
            }

            rubrics.add(
                ScoringRubricEntity(
                    id = "rubric_${q.id}",
                    questionId = q.id,
                    applicableDimensions = applicableDims,
                    dimensionWeightsJson = """{"satya":1.2,"dharma":1.4,"karuna":1.1,"nyaya":1.3,"samyama":1.2,"dhairya":1.1,"nishtha":1.0,"viveka":1.3}""",
                    scoringCriteria = "Evaluates truthfulness, restraint over reactive rage, accountability to duties, and compassionate long-term wisdom without petty vengeance.",
                    reasonableAlternativeApproaches = "Addresses the situation factually without public slander, preserving dignity, seeking constructive de-escalation, and upholding duty."
                )
            )
        }
        return rubrics
    }
}
